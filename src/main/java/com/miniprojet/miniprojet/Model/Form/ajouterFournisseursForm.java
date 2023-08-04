package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class ajouterFournisseursForm implements Serializable {

    @NotNull(message = "{Nom.notnull}")
    @Size(min=1, message = "{Nom.notempty}")
    private String nom;
}