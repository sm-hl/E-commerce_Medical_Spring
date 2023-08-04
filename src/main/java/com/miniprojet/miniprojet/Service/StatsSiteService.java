package com.miniprojet.miniprojet.Service;

import java.util.Date;

import com.miniprojet.miniprojet.Model.StatsSite;
import com.miniprojet.miniprojet.Repository.StatsSiteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsSiteService {
    @Autowired StatsSiteRepository statsSiteRepository;

    /**
     * ajouter un nombre de visiteurs au stats d'aujourd'hui
     * @param nb
     */
    public void ajouterVisiteur(int nb)
    {
        try
        {
            StatsSite stats = statsSiteRepository.recupererStatsAvecDate(new Date());
            if(stats == null)
            {
                stats = new StatsSite();
                stats.setNbUsersToday(0);
                stats.setDate(new Date());
            }
            stats.setNbUsersToday(stats.getNbUsersToday() + nb);
            statsSiteRepository.save(stats);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return Nb de visiteurs qui ont visité le site aujourd'hui
     */
    public int recupererNbVisiteurAujourdhui()
    {
        try 
        {
            StatsSite stats = statsSiteRepository.recupererStatsAvecDate(new Date());
            if(stats == null) return 0;
            return stats.getNbUsersToday();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 
     * @return Nb de visiteurs qui ont visité le site globalement
     */
    public int recupererNbVisiteurTotal()
    {
        try 
        {
            Iterable<StatsSite> statsList = statsSiteRepository.findAll();
            int nb = 0;
            for (StatsSite statsSite : statsList) {
                nb += statsSite.getNbUsersToday();
            }
            return nb;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
