package com.miniprojet.miniprojet.Repository;

import com.miniprojet.miniprojet.Model.Stock;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StockRepository extends CrudRepository<Stock, Integer>{
    @Query("from Stock where id_produit = :idProduit")
    Stock chercherProduit(@Param("idProduit") int idProduit);
    
}

