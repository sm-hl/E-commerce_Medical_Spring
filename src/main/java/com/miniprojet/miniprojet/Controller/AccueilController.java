package com.miniprojet.miniprojet.Controller;

import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Service.CompteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AccueilController {
    //En injecte compteService pour qu'il devient disponible dans cette classe
    @Autowired CompteService compteService;

    @GetMapping(path="") // Méthode invoqué pour GET de lien "/"
    public String acceuil(Model model) // Model est une classe qui permet l'échange entre les Views et le Controlleur
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

        return "Accueil"; // la methode GET retourne à la fin le nom du jsp (Acceuil.jsp)
    }
}
