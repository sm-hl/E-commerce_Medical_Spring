package com.miniprojet.miniprojet.Repository;

import java.util.List;

import com.miniprojet.miniprojet.Model.Produit;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProduitRepository extends CrudRepository<Produit, Integer>{
    @Query("from Produit where categorieProduit = :categorie and isDeleted = :deleted")
    List<Produit> chercherProduit(@Param("categorie") String categorie, @Param("deleted") boolean deleted);

    @Query("from Produit where nomProduit = :nomProduit and isDeleted = :deleted")
    Produit chercherProduitAvecNom(@Param("nomProduit") String nomProduit, @Param("deleted") boolean deleted);
}

