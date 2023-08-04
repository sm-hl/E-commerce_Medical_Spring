package com.miniprojet.miniprojet.Model;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Promo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name="idProduit", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private float prixPromo;

    @Column(nullable = false)
    private Date dateExpiration;
}
