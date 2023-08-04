package com.miniprojet.miniprojet.Controller;

import java.util.List;

import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Model.Stock;
import com.miniprojet.miniprojet.Service.CompteService;
import com.miniprojet.miniprojet.Service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Produits")
public class ProduitsController {
    @Autowired StockService stockService;
    @Autowired CompteService compteService;
    
    @GetMapping(path="")
    public String AllProduitsStock(Model model, String q) // q : la zone de recherche
    {
        Compte compte = null;

        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            //Si non, on affecte la variable "user" = null au jsp
            model.addAttribute("user", null);
        else
        {
            //Si oui, on affecte la variable "user" = compte au jsp
            compte = compteService.recupererCompteActuel();
            model.addAttribute("user", compte);
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

        List<Stock> stocks = stockService.recupererStock(false);
        if(q == null)
        {
            model.addAttribute("stocks", stocks);
        }
        else
        {
            model.addAttribute("stocks", stockService.chercherProduits(stocks, q));
        }

        return "Produits";
    }
}
