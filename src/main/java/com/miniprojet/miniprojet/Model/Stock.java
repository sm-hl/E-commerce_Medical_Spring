package com.miniprojet.miniprojet.Model;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name="idProduit", nullable = false)
    private Produit produit;

    @Column(nullable = false)
    private int nbStock;

    @Column(nullable = false)
    private boolean isHidden;
}
