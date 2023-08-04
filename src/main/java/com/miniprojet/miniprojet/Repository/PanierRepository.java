package com.miniprojet.miniprojet.Repository;

import java.util.List;

import com.miniprojet.miniprojet.Model.ClientProduitId;
import com.miniprojet.miniprojet.Model.Panier;

import org.springframework.data.repository.CrudRepository;

public interface PanierRepository extends CrudRepository<Panier, ClientProduitId>{

    List<Panier> findByIdIdClient(int idClient);
}
