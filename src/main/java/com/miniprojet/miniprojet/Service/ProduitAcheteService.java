package com.miniprojet.miniprojet.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.miniprojet.miniprojet.Model.Client;
import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Model.ProduitAchete;
import com.miniprojet.miniprojet.Repository.ProduitAcheteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitAcheteService {
    @Autowired ProduitAcheteRepository produitAcheteRepository;

    /**
     * Enregister un nouveau achat entre client et produit dans cet instant
     * @param client : doit être déjà existe dans la base de donnée
     * @param produit : doit être déjà existe dans la base de donnée
     * @param quantite : Nombre achetées
     * @return <code>True</code> en succès, <code>False</code> en échec
     */
    public boolean EnregistrerAchat(Client client, Produit produit, int quantite)
    {
        ProduitAchete produitAchete = new ProduitAchete();
        produitAchete.setClient(client);
        produitAchete.setProduit(produit);
        produitAchete.setNbAchat(quantite);
        produitAchete.setDateAchat(new Date());

        try
        {
            produitAcheteRepository.save(produitAchete);
        }
        catch(Exception e)
        {
            return false;
        }

        return true;
    }

    /**
     * TODO:
     * Cette méthode va utiliser des requêtes SQL pour calculer chiffres d'affaires,
     * mais comme la table "ProduitAchete" est croissante, cela arrivera plus tard
     * à un tableau avec plus de 10000 lignes, donc cette méthode va prendre beaucoup
     * du temps à executer.
     * La solution est de convertir cette méthode à une table séparée
     */
    public float ChiffresAffaires(int jours1, int jours2)
    {
        float chiffresAffaires = 0;
        List<ProduitAchete> result = produitAcheteRepository.recupererAchatsAvecIntervalle(jours1, jours2);
        for(ProduitAchete produitAchete : result)
        {
            Produit produit =  produitAchete.getProduit();
            chiffresAffaires += (produit.getPrixProduit() - produit.getPrixFournisseur())*produitAchete.getNbAchat();
        }
        return chiffresAffaires;
    }

    public float ChiffresAffaires()
    {
        float chiffresAffaires = 0;
        List<ProduitAchete> result  = new ArrayList<ProduitAchete>();
        produitAcheteRepository.findAll().iterator().forEachRemaining(result::add);
        
        for(ProduitAchete produitAchete : result)
        {
            Produit produit =  produitAchete.getProduit();
            chiffresAffaires += (produit.getPrixProduit() - produit.getPrixFournisseur())*produitAchete.getNbAchat();
        }
        return chiffresAffaires;
    }

    public ProduitAchete chercherAchatAvecID(int id)
    {
        ProduitAchete result = null;
        try
        {
            result = produitAcheteRepository.findById(id).get();
        }
        catch(Exception e) {}

        return result;
    }
}
