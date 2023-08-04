package com.miniprojet.miniprojet.Controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.miniprojet.miniprojet.Model.Fournisseur;
import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Model.Stock;
import com.miniprojet.miniprojet.Model.Form.*;
import com.miniprojet.miniprojet.Repository.FournisseurRepository;
import com.miniprojet.miniprojet.Repository.StockRepository;
import com.miniprojet.miniprojet.Service.CompteService;
import com.miniprojet.miniprojet.Service.FournisseurService;
import com.miniprojet.miniprojet.Service.ProduitAcheteService;
import com.miniprojet.miniprojet.Service.ProduitService;
import com.miniprojet.miniprojet.Service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired CompteService compteService;
    @Autowired FournisseurService fournisseurService;
    @Autowired FournisseurRepository fournisseurRepository;
    @Autowired ProduitService produitService;
    @Autowired StockService stockService;
    @Autowired StockRepository stockRepository;
    @Autowired ProduitAcheteService produitAcheteService;

    @GetMapping(path="")
    public String acceuil(){
        

        return "redirect:/Admin/stats";
    }

    /////////////////////////////Gestion des produits//////////////////////////////////////////

    @GetMapping(path="/ajouterProduits")
    public String GETajouterProduits(Model model){

        model.addAttribute("active", "ajouterProduits");
        model.addAttribute("activePanel", "gestion des produits");
        model.addAttribute("fournisseurs", fournisseurService.recupererFournisseurs());

        return "ajouterProduits";
    }

    @PostMapping(path="/ajouterProduits")
    public String POSTajouterProduits(final @Valid  ajouterProduitsForm form, final BindingResult bindingResult, Model model){
        
        model.addAttribute("active", "ajouterProduits");
        model.addAttribute("activePanel", "gestion des produits");
        model.addAttribute("fournisseurs", fournisseurService.recupererFournisseurs());

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ajouterProduits";
        }

        Fournisseur fournisseur = null;

        try {
            fournisseur = fournisseurRepository.findById(Integer.parseInt(form.getFournisseur())).get();
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "Fournisseur inconnue!");
            return "ajouterProduits";
        }
        

        Produit produit = new Produit();
        produit.setCategorieProduit(form.getCategorie());
        produit.setFournisseur(fournisseur);
        produit.setNomProduit(form.getNom());
        produit.setPrixFournisseur(Float.parseFloat(form.getPrixAchat()));
        produit.setPrixProduit(Float.parseFloat(form.getPrix()));

        MultipartFile photo = form.getImage();

        if(photo.getOriginalFilename() != null && !photo.getOriginalFilename().isEmpty())
        {
            produitService.uploadImageToProduit(photo, produit);
        }

        if(produitService.ajouterProduit(produit, fournisseur))
            model.addAttribute("success", "Produit ajouté!");

        return "ajouterProduits";
    }

    @GetMapping(path="/supprimerProduits")
    public String GETsupprimerProduits(Model model){

        model.addAttribute("active", "supprimerProduits");
        model.addAttribute("activePanel", "gestion des produits");
        model.addAttribute("produits", produitService.recupererTousProduits(false));

        return "supprimerProduits";
    }

    @PostMapping(path="/supprimerProduits")
    public String POSTsupprimerProduits(final @Valid  supprimerProduitsForm form, final BindingResult bindingResult, Model model){

        model.addAttribute("active", "supprimerProduits");
        model.addAttribute("activePanel", "gestion des produits");
        

        if(bindingResult.hasErrors()){
            model.addAttribute("error", "Echec de suppresion du produit!");
            return "supprimerProduits";
        }
        try {
            produitService.supprimerProduit(produitService.recupererProduitAvecId(Integer.parseInt(form.getId())));
        } catch (Exception e) {
            model.addAttribute("error", "Echec de suppresion du produit!");
            return "supprimerProduits";
        }
        model.addAttribute("success", "Produit supprimé!");
        model.addAttribute("produits", produitService.recupererTousProduits(false));

        return "supprimerProduits";
    }



    /////////////////////////////Gestion des fournisseurs//////////////////////////////////////////

    @GetMapping(path="/ajouterFournisseurs")
    public String GETajouterFournisseurs(Model model){

        model.addAttribute("active", "ajouterFournisseurs");
        model.addAttribute("activePanel", "gestion des fournisseurs");
        model.addAttribute("fournisseurs", fournisseurService.recupererFournisseurs());

        return "ajouterFournisseurs";
    }

    @PostMapping(path="/ajouterFournisseurs")
    public String POSTajouterFournisseurs(final @Valid  ajouterFournisseursForm form, final BindingResult bindingResult, Model model){
        
        model.addAttribute("active", "ajouterFournisseurs");
        model.addAttribute("activePanel", "gestion des fournisseurs");
        model.addAttribute("fournisseurs", fournisseurService.recupererFournisseurs());

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ajouterFournisseurs";
        }
        
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNomFournisseur(form.getNom());

        if (fournisseurService.ajouterFournisseur(fournisseur))
            model.addAttribute("success", "Fournisseur ajouté!");

        return "ajouterFournisseurs";
    }

    @GetMapping(path="/supprimerFournisseurs")
    public String GETsupprimerFournisseurs(Model model){

        model.addAttribute("active", "supprimerFournisseurs");
        model.addAttribute("activePanel", "gestion des fournisseurs");
        model.addAttribute("fournisseurs", fournisseurService.recupererFournisseurs());

        return "supprimerFournisseurs";
    }

    @PostMapping(path="/supprimerFournisseurs")
    public String POSTsupprimerFournisseurs(final @Valid  supprimerFournisseursForm form, final BindingResult bindingResult, Model model){

        model.addAttribute("active", "supprimerFournisseurs");
        model.addAttribute("activePanel", "gestion des fournisseurs");

        if(bindingResult.hasErrors()){
            model.addAttribute("error", "Echec de suppresion du fournisseur!");
            return "supprimerFournisseurs";
        }
        try {
            fournisseurService.supprimerFournisseur(fournisseurRepository.findById(Integer.parseInt(form.getId())).get());
        } catch (Exception e) {
            model.addAttribute("error", "Echec de suppresion du fournisseur!");
            return "supprimerFournisseurs";
        }
        model.addAttribute("success", "Fournisseur supprimé!");
        model.addAttribute("fournisseurs", fournisseurService.recupererFournisseurs());

        return "supprimerFournisseurs";
    }



    /////////////////////////////Gestion du stock//////////////////////////////////////////

    @GetMapping(path="/ajouterStocks")
    public String GETajouterStocks(Model model){

        model.addAttribute("active", "ajouterStocks");
        model.addAttribute("activePanel", "gestion des Stocks");
        model.addAttribute("produits", produitService.recupererTousProduits(false));

        return "ajouterStocks";
    }

    @PostMapping(path="/ajouterStocks")
    public String POSTajouterStocks(final @Valid  ajouterStocksForm form, final BindingResult bindingResult, Model model){
        
        model.addAttribute("active", "ajouterStocks");
        model.addAttribute("activePanel", "gestion des Stocks");
        model.addAttribute("produits", produitService.recupererTousProduits(false));

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "ajouterStocks";
        }
        Produit produit = null;
        try {
            produit = produitService.recupererProduitAvecId(Integer.parseInt(form.getIdProduit()));
        } catch (Exception e) {
            model.addAttribute("error", "Produit invalid!");
            return "ajouterStocks";
        }
        
        if (stockService.ajouterProduit(produit, Integer.parseInt(form.getNbStock())))
            model.addAttribute("success", "Stock ajouté!");

        return "ajouterStocks";
    }

    @GetMapping(path="/modifierStocks")
    public String GETmodifierStocks(Model model){

        model.addAttribute("active", "modifierStocks");
        model.addAttribute("activePanel", "gestion des Stocks");
        model.addAttribute("stocks", stockService.recupererStock(false));

        return "modifierStocks";
    }

    @PostMapping(path="/modifierStocks")
    public String POSTmodifierStocks(final @Valid  modifierStocksForm form, final BindingResult bindingResult, Model model){

        model.addAttribute("active", "modifierStocks");
        model.addAttribute("activePanel", "gestion des Stocks");
        model.addAttribute("stocks", stockService.recupererStock(false));

        if(bindingResult.hasErrors()){
            model.addAttribute("error", "Erreur lors de modification du produit dans le stock");
            return "modifierStocks";
        }

        try {
            Stock stock = stockRepository.findById(Integer.parseInt(form.getIdStock())).get();
            stock.setNbStock(Integer.parseInt(form.getNbStock()));
            stockRepository.save(stock);
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de modification du produit dans le stock");
        }
        
        model.addAttribute("success", "Produit changé!");

        return "modifierStocks";
    }

    @GetMapping(path="/supprimerStocks")
    public String GETsupprimerStocks(Model model){

        model.addAttribute("active", "modifierStocks");
        model.addAttribute("activePanel", "gestion des Stocks");
        model.addAttribute("stocks", stockService.recupererStock(false));

        return "modifierStocks";
    }

    @PostMapping(path="/supprimerStocks")
    public String POSTsupprimerStocks(final @Valid  supprimerStocksForm form, final BindingResult bindingResult, Model model){

        model.addAttribute("active", "modifierStocks");
        model.addAttribute("activePanel", "gestion des Stocks");

        if(bindingResult.hasErrors()){
            model.addAttribute("error", "Echec d'enlever le produit du stock!");
            return "modifierStocks";
        }
        try {
            stockService.supprimerProduit(stockRepository.findById(Integer.parseInt(form.getIdStock())).get());
        } catch (Exception e) {
            model.addAttribute("error", "Echec d'enlever le produit du stock!");
            return "modifierStocks";
        }
        model.addAttribute("success", "Produit enlevé!");
        model.addAttribute("stocks", stockService.recupererStock(false));

        return "modifierStocks";
    }

    @GetMapping(path="/stats")
    public String GETstats(Model model){
        String[] monthName = {"Janvier", "Fevrier",
                "Mars", "Avril", "Mai", "Juin", "Juillet",
                "Aout", "Septembre", "Octobre", "Novembre",
                "Decembre"};

        Calendar cal = Calendar.getInstance();

        float resultatTotal = produitAcheteService.ChiffresAffaires();

        HashMap<String, Float> resultatMois = new HashMap<String, Float>();

        for (int index = 0; index < 6; index++) {
            
            String moisActuel;
            if(cal.get(Calendar.MONTH) - index < 0)
            {
                moisActuel = monthName[cal.get(Calendar.MONTH)- index + 12];
            }
            else
            {
                moisActuel = monthName[cal.get(Calendar.MONTH) - index];
            }   
            Float resultatActuel = produitAcheteService.ChiffresAffaires(30+30*index, 30*index);
            resultatMois.put(moisActuel, resultatActuel);
        }

        model.addAttribute("activePanel", "stats du site");
        model.addAttribute("resultatTotal", resultatTotal);
        model.addAttribute("resultatMois", resultatMois);

        return "stats";
    }
}
