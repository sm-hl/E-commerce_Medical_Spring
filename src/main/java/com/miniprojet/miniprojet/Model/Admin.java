package com.miniprojet.miniprojet.Model;

import javax.persistence.*;

@Entity
public class Admin extends Personne {
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
