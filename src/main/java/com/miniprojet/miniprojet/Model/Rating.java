package com.miniprojet.miniprojet.Model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Rating {
    @EmbeddedId
    private ClientProduitId id = new ClientProduitId();

    @ManyToOne
    @MapsId("idProduit")
    private Produit produit;

    @ManyToOne
    @MapsId("idClient")
    private Client client;

    @Column(nullable = false)
    private float rate;
}
