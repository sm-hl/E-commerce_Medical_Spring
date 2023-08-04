package com.miniprojet.miniprojet.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Model.Promo;
import com.miniprojet.miniprojet.Repository.PromoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromoService {
    @Autowired PromoRepository promoRepository;

    /**
     * Appliquer un promo sur un produit
     * @param promo
     * @param produit : doit être déjà existe dans la base de donnée
     * @return
     */
    public boolean ajouterPromo(Promo promo, Produit produit)
    {
        try
        {
            promoRepository.save(promo);
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }

    public void supprimerPromo(Promo promo)
    {
        promoRepository.delete(promo);
    }

    /**
     * Verifier si le promo est expiré, puis le supprimer si est le cas
     * @param promo
     * @return <code>True</code> s'il est expiré et supprimé, <code>False</code> sinon
     */
    public boolean expirerPromo(Promo promo)
    {
        LocalDate date = Instant.ofEpochMilli(promo.getDateExpiration().getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
        
        LocalDate now = LocalDate.now();

        if (now.getYear() <= date.getYear())
            if(now.getMonthValue() <= date.getMonthValue())
                if(now.getDayOfMonth() <= date.getDayOfMonth())
                    return false;
        
        promoRepository.delete(promo);
        return true;
    }

    /**
     * Récuperer tout les promos disponibles (non expirés)
     * @return <code>List</code> de type <code>Promo</code>
     */
    public List<Promo> recupererPromos()
    {
        Iterable<Promo> result = promoRepository.findAll();
        List<Promo> promos = new ArrayList<Promo>();
        
        for (Promo promo : result)
        {
            if(!expirerPromo(promo)) promos.add(promo);
        }

        return promos;
    }
}
