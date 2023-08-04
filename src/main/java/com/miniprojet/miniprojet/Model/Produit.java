package com.miniprojet.miniprojet.Model;

import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="idFournisseur", nullable=false)
    private Fournisseur fournisseur;

    @Column(name="nomProduit" ,nullable = false)
    private String nomProduit;

    @Column(nullable = false)
    private String categorieProduit;

    @Column(nullable = false)
    private float prixFournisseur;

    @Column(nullable = false)
    private float prixProduit;

    private String urlImageProduit;

    @Column(length = 1000)
    private String descriptionProduit;

    @Column(nullable = false)
    private boolean isDeleted;

    @OneToMany(mappedBy = "produit")
    private Set<ProduitAchete> produitAchetes;

    @OneToMany(mappedBy = "produit")
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "produit")
    private Set<Panier> paniers;

    @OneToOne(mappedBy = "produit")
    private Promo promo;

    @OneToOne(mappedBy = "produit")
    private Stock stock;
}
