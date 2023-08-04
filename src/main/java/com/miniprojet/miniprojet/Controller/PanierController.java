package com.miniprojet.miniprojet.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.miniprojet.miniprojet.Model.Client;
import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Model.OrderDetail;
import com.miniprojet.miniprojet.Model.Panier;
import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Model.Stock;
import com.miniprojet.miniprojet.Service.CompteService;
import com.miniprojet.miniprojet.Service.PanierService;
import com.miniprojet.miniprojet.Service.PaymentServices;
import com.miniprojet.miniprojet.Service.ProduitAcheteService;
import com.miniprojet.miniprojet.Service.ProduitService;
import com.miniprojet.miniprojet.Service.StockService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Panier")
public class PanierController {
    @Autowired ProduitService produitService;
    @Autowired StockService stockService;
    @Autowired CompteService compteService;
    @Autowired PanierService panierService;
    @Autowired PaymentServices paymentServices;
    @Autowired ProduitAcheteService produitAcheteService;

    @GetMapping(path="")
    public String getPanier(HttpServletRequest request, Model model)
    {
        Compte compte = null;
        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            model.addAttribute("user", null);
        else
        {
            //Si oui, on affecte la variable "user" = compte au jsp
            compte = compteService.recupererCompteActuel();
            model.addAttribute("user", compteService.recupererCompteActuel());
        }

        //Est ce que le compte est Admin ou client?
        if(compte != null)
        {
            if(compte.getIsAdmin())
            {
                model.addAttribute("personne", compte.getAdmin());
            }
            else
            {
                model.addAttribute("personne", compte.getClient());
            }
        }


        Cookie[] cookies = request.getCookies();
        List<Stock> allStock = stockService.recupererStock(false);
        List<Stock> stocks = new ArrayList<Stock>();
        for (Cookie cookie : cookies) {
            try {
                Produit produit = produitService.recupererProduitAvecNom(cookie.getName());
                if(produit == null) continue;
                for (Stock stock : allStock) {
                    if(produit.getId() == stock.getProduit().getId())
                    {
                        stocks.add(stock);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }

        model.addAttribute("stocks", stocks);

        return "Panier";
    }

    @GetMapping(path="/enregistrer")
    public String enregistrerPanier(HttpServletRequest request, Model model)
    {
        Compte compte = null;
        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
        {
            model.addAttribute("user", null);
            return "Panier";
        }
        else
        {
            //Si oui, on affecte la variable "user" = compte au jsp
            compte = compteService.recupererCompteActuel();
            model.addAttribute("user", compteService.recupererCompteActuel());
        }

        Cookie[] cookies = request.getCookies();
        List<Stock> allStock = stockService.recupererStock(false);
        List<Stock> stocks = new ArrayList<Stock>();
        HashMap<Integer, Integer> panierTemp = new HashMap<Integer, Integer>();
        for (Cookie cookie : cookies) {
            try {
                Produit produit = produitService.recupererProduitAvecNom(cookie.getName());
                if(produit == null) continue;
                for (Stock stock : allStock) {
                    if(produit.getId() == stock.getProduit().getId())
                    {
                        stocks.add(stock);
                        panierTemp.put(produit.getId(), Integer.parseInt(cookie.getValue()));
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                continue;
            }
        }
        //Est ce que le compte est Admin ou client?
        if(compte != null)
        {
            if(compte.getIsAdmin())
            {
                model.addAttribute("personne", compte.getAdmin());
                model.addAttribute("stocks", stocks);

                return "Panier";
            }
            else
            {
                Client client = compte.getClient();
                for (Panier panier : panierService.recupererPaniers(client)) {
                    panierService.supprimerPanier(panier);
                }
                for (HashMap.Entry<Integer, Integer> entry : panierTemp.entrySet()) {
                    panierService.ajouterProduitAuPanier(client, produitService.recupererProduitAvecId(entry.getKey()), entry.getValue());
                }
                model.addAttribute("personne", client);
            }
        }

        model.addAttribute("status", "saved");
        model.addAttribute("stocks", stocks);

        return "Panier";
    }


    @GetMapping(path="/achat")
    public String authorize_payment(HttpServletRequest request, final Model model)
    {
        OrderDetail orderDetail = new OrderDetail();

        Compte compte = null;
        Client client = null;
        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            return "redirect:/";
        else
        {
            //Si oui, on affecte la variable "user" = compte au jsp
            compte = compteService.recupererCompteActuel();
        }

        //Est ce que le compte est Admin ou client?
        if(compte != null)
        {
            if(compte.getIsAdmin())
            {
                return "redirect:/Panier"; // Admin normalement ne doit pas acheter
            }
            else
            {
                client = compte.getClient();
            }
        }
        
        List<Panier> paniers = panierService.recupererPaniers(client);
        float total = 0;
        for (Panier panier : paniers) {
            total += panier.getProduit().getPrixProduit() * panier.getNbProduit();
        }

        orderDetail.setPanier(paniers);
        orderDetail.setTotal(total / 10);
        
        try {
            String approvalLink = paymentServices.authorizePayment(orderDetail);
 
            return "redirect:"+approvalLink;
             
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    @GetMapping(path="/review_payment")
    public String review_payment(@Param("paymentId") String paymentId, @Param("PayerID") String PayerID, final Model model)
    {   
        Compte compte = null;
        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            return "redirect:/";
        else
        {
            //Si oui, on affecte la variable "user" = compte au jsp
            compte = compteService.recupererCompteActuel();
        }

        //Est ce que le compte est Admin ou client?
        if(compte != null)
        {
            if(compte.getIsAdmin())
            {
                return "redirect:/Panier"; // Admin normalement ne doit pas acheter
            }
        }
        
        try {
            Payment payment = paymentServices.getPaymentDetails(paymentId);
             
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
             
            model.addAttribute("payer", payerInfo);
            model.addAttribute("transaction", transaction);
            model.addAttribute("paymentId", paymentId);
            model.addAttribute("PayerID", PayerID);

            return "Review";
             
        } catch (PayPalRESTException ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    @PostMapping(path="/execute_payment")
    public String execute_payment(@Param("paymentId") String paymentId, @Param("PayerID") String PayerID, final Model model)
    {     
        Compte compte = null;
        Client client = null;
        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            return "redirect:/";
        else
        {
            //Si oui, on affecte la variable "user" = compte au jsp
            compte = compteService.recupererCompteActuel();
        }

        //Est ce que le compte est Admin ou client?
        if(compte != null)
        {
            if(compte.getIsAdmin())
            {
                return "redirect:/Panier"; // Admin normalement ne doit pas acheter
            }
            else
            {
                client = compte.getClient();
            }
        }

        try {
            paymentServices.executePayment(paymentId, PayerID);
            //S'il est effectué sans exception

            List<Panier> paniers = panierService.recupererPaniers(client);
            for (Panier panier : paniers) {
                produitAcheteService.EnregistrerAchat(client, panier.getProduit(), panier.getNbProduit());
            }

            return "receipt";
             
        } catch (PayPalRESTException ex) { //Echec de transaction
            ex.printStackTrace();
            return "error";
        }
    }
}
