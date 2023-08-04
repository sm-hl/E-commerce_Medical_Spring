package com.miniprojet.miniprojet.Controller;

import java.util.List;

import com.miniprojet.miniprojet.Model.Fournisseur;
import com.miniprojet.miniprojet.Service.FournisseurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fournisseurs")
public class FournisseursController {
    @Autowired FournisseurService fournisseurService;
    
    @GetMapping(path="")
    public String fournisseurs(Model model)
    {
        List<Fournisseur> fournisseursList = fournisseurService.recupererFournisseurs();
        
        model.addAttribute("fournisseursList", fournisseursList);

        return "fournisseurs";
    }
}
