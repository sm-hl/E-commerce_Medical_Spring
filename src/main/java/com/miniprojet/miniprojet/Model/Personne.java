package com.miniprojet.miniprojet.Model;

import javax.persistence.*;

import lombok.Data;

@Data
@MappedSuperclass
public class Personne {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String nomComplet;
    private String tel;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String pays;

    @Column(nullable = false)
    private String province;

    private String imageUrl = "/images/ProfileDefault.png";
}
