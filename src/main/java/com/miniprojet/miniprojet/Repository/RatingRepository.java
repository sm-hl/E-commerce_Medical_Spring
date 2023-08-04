package com.miniprojet.miniprojet.Repository;

import java.util.List;

import com.miniprojet.miniprojet.Model.ClientProduitId;
import com.miniprojet.miniprojet.Model.Rating;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends CrudRepository<Rating, ClientProduitId>{
    @Query("from Rating where idProduit = :idProduit")
    List<Rating> recupererRatings(@Param("idProduit") int idProduit);
}
