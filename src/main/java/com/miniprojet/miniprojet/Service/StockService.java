package com.miniprojet.miniprojet.Service;

import java.util.ArrayList;
import java.util.List;

import com.miniprojet.miniprojet.Model.Produit;
import com.miniprojet.miniprojet.Model.Stock;
import com.miniprojet.miniprojet.Repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired StockRepository stockRepository;

    /**
     * Ajouter un quantite de produit au stock
     * Remarque : si nbStock est égale à 0, le produit ne vas pas être supprimé
     * @param produit
     * @param quantite : il peut être négative, mais le nbStock ne peux pas diminuer sous 0
     */
    public boolean ajouterProduit(Produit produit, int quantite)
    {
        Stock stock = stockRepository.chercherProduit(produit.getId());
        if (stock == null)
        {
            stock = new Stock();
            stock.setNbStock(0);
            stock.setHidden(false);
            stock.setProduit(produit);
        }

        stock.setNbStock(stock.getNbStock() + quantite);
        if (stock.getNbStock() < 0) stock.setNbStock(0);

        try
        {
            stockRepository.save(stock);
        }
        catch(Exception e) {return false;}

        return true;
    }

    /**
     * Recupérer totalement le stock dans une liste
     * @param hidden <code>boolean</code> : Est ce que les produits cachés
     * doivent être récupérée aussi?
     * @return <code>List</code> de type <code>Stock</code>
     */
    public List<Stock> recupererStock(boolean hidden)
    {
        Iterable<Stock> result = stockRepository.findAll();
        List<Stock> list = new ArrayList<Stock>();

        for (Stock stock : result)
        {
            if(!hidden)
            {
                if(!stock.isHidden())
                    list.add(stock);
            }
            else
                list.add(stock);
        }

        return list;
    }

    /**
     * Supprimer le produit totalement dans le stock
     * @param stock
     */
    public void supprimerProduit(Stock stock)
    {
        stockRepository.deleteById(stock.getId());
    }

    /**
     * Cacher ou Afficher un produit dans le stock
     * @param produit
     * @param hidden <code>boolean</code> : Est ce que le produit doit être 
     * cachée (<code>true</code>), ou affichée (<code>false</code>)?
     */
    public void cacherProduit(Produit produit, boolean hidden)
    {
        Stock stock = stockRepository.chercherProduit(produit.getId());
        stock.setHidden(hidden);
        stockRepository.save(stock);
    }

    public List<Stock> chercherProduits(List<Stock> listInitial, String mot)
    {
        List<Stock> listFinale = new ArrayList<Stock>();
        for (Stock stock : listInitial) {
            if(stock.getProduit().getNomProduit().contains(mot) || stock.getProduit().getCategorieProduit().compareTo(mot) == 0)
                listFinale.add(stock);
        }

        return listFinale;
    }
}
