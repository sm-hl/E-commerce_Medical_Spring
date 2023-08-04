package com.miniprojet.miniprojet.Service;

import java.util.ArrayList;
import java.util.List;

import com.miniprojet.miniprojet.Model.Fournisseur;
import com.miniprojet.miniprojet.Repository.FournisseurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {
    @Autowired FournisseurRepository fournisseurRepository;

    public boolean ajouterFournisseur(Fournisseur fournisseur)
    {
        try
        {
            fournisseurRepository.save(fournisseur);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public void supprimerFournisseur(Fournisseur fournisseur)
    {
        fournisseurRepository.deleteById(fournisseur.getId());
    }

    public List<Fournisseur> recupererFournisseurs()
    {
        List<Fournisseur> list = new ArrayList<Fournisseur>();
        fournisseurRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
