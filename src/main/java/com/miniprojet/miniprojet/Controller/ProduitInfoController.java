package com.miniprojet.miniprojet.Controller;

import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Service.CompteService;
import com.miniprojet.miniprojet.Service.ProduitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Produit")
public class ProduitInfoController {
    @Autowired CompteService compteService;
    @Autowired ProduitService produitService;
    
    @GetMapping(path="/{id}")
    public String AllProduitsStock(Model model,@PathVariable("id") @Nullable String id) // id : ID du produit
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

        //Si id est introuvable
        if(id == null)
        {
            return "redirect:/Produits";
        }
        else
        {
            try
            {
                model.addAttribute("produit", produitService.recupererProduitAvecId(Integer.parseInt(id)));
            }
            catch(NumberFormatException e)
            {
                return "redirect:/Produits";
            }
        }

        

        return "Produit";
    }
}
