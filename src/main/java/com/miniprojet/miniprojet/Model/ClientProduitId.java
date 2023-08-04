package com.miniprojet.miniprojet.Model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Embeddable
@Data
public class ClientProduitId implements Serializable{
    private static final long serialVersionUID = 1L;

    private int idClient;
    private int idProduit;
}
