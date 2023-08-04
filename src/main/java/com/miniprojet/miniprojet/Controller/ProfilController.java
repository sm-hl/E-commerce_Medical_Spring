package com.miniprojet.miniprojet.Controller;

import javax.validation.Valid;

import com.miniprojet.miniprojet.Model.Compte;
import com.miniprojet.miniprojet.Model.Form.ProfilForm;
import com.miniprojet.miniprojet.Service.CompteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class ProfilController {
    @Autowired CompteService compteService;
    
    @GetMapping(path="Profil")
    public String getProfil(Model model)
    {
        Compte compte = null;

        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            return "Acceuil";
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

        return "Profil";
    }

    @PostMapping("/Profil")
    public String profil(final @Valid ProfilForm form, final BindingResult bindingResult, final Model model)
    {
        Compte compte = null;

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
        }

        //Verifions si l'utilisatuer actuel est connecté avec SecurityContext
        if(SecurityContextHolder.getContext().getAuthentication() == null)
            return "Acceuil";
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

        //Modifier le compte
        if (form.getNomComplet() != null)
            compteService.miseAjourCompte(compte, "nomComplet", form.getNomComplet());
        if (form.getAdresse() != null)
            compteService.miseAjourCompte(compte, "adresse", form.getAdresse());
        if (form.getPays() != null)
            compteService.miseAjourCompte(compte, "pays", form.getPays());
        if (form.getProvince() != null)
            compteService.miseAjourCompte(compte, "province", form.getProvince());
        if (form.getTel() != null)
            compteService.miseAjourCompte(compte, "tel", form.getTel());
        
        return "Profil";
    }

    @PostMapping("/Profil/modifyIcon")
    public String modifyIcon(@RequestParam("photo") MultipartFile photo, final Model model)
    {
        if(photo.getOriginalFilename() == null || photo.getOriginalFilename().isEmpty()) return "redirect:/Profil";

        Compte compte = compteService.recupererCompteActuel();

        if(compte == null) return "redirect:/";
        //Modifier l'image
        compteService.uploadImageToCompte(photo, compte);
        
        return "redirect:/Profil";
    }
}
