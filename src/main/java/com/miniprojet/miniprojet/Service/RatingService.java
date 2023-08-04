package com.miniprojet.miniprojet.Service;

import java.util.List;

import com.miniprojet.miniprojet.Model.Client;
import com.miniprojet.miniprojet.Model.ClientProduitId;
import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Model.Rating;
import com.miniprojet.miniprojet.Repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired RatingRepository ratingRepository;

    /**
     * Ajouter/Modifier un évaluation (rating) entre client et produit
     * @param client : doit être déjà existe dans la base de donnée
     * @param produit : doit être déjà existe dans la base de donnée
     * @param rate
     * @return : <code>True</code> en succès, <code>False</code> en échec
     */
    public boolean ajouterRating(Client client, Produit produit, float rate)
    {
        //Verification si rating existe déjà
        ClientProduitId id = new ClientProduitId();
        id.setIdClient(client.getId());
        id.setIdProduit(produit.getId());
        Rating rating = new Rating();
        try {
            rating = ratingRepository.findById(id).get();
        } catch (Exception e) // Il n'existe pas
        {
            rating.setClient(client);
            rating.setProduit(produit);
        }
        rating.setRate(rate);

        try {
            ratingRepository.save(rating);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Determiner l'évaluation d'un produit
     * @param produit
     * @return <code>Float</code>
     */
    public float averageRate(Produit produit)
    {
        List<Rating> ratings = ratingRepository.recupererRatings(produit.getId());
        float rate = 0;
        for(Rating rating : ratings)
        {
            rate += rating.getRate();
        }

        return rate;
    }
}
