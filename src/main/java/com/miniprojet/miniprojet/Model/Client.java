package com.miniprojet.miniprojet.Model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Client extends Personne {
    @OneToMany(mappedBy = "client")
    private Set<ProduitAchete> produitAchetes;

    @OneToMany(mappedBy = "client")
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "client")
    private Set<Panier> paniers;

    @OneToOne
    @JoinColumn(name="idCompte", nullable = false)
    private Compte compte;

    public Compte getCompte() {
        return this.compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
