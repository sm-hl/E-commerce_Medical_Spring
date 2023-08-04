package com.miniprojet.miniprojet.Model;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class ProduitAchete {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="idProduit", nullable=false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name="idClient", nullable=false)
    private Client client;

    @Column(nullable = false)
    private int nbAchat;

    @Column(nullable = false)
    private Date dateAchat;
}
