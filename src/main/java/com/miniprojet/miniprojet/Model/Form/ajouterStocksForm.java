package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class ajouterStocksForm implements Serializable {
    
    //Infos sur le produit
    @NotNull(message = "{Produit.invalid}")
    @Size(min=1, message = "{Produit.invalid}")
    private String idProduit;

    @NotNull(message = "{Nb.notnull}")
    @Size(min=1, message = "{Nb.notempty}")
    private String nbStock;
}