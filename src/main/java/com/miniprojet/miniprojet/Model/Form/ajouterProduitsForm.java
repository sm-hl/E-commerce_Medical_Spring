package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

import java.io.Serializable;

@Data
public class ajouterProduitsForm implements Serializable {
    
    //Infos sur le compte
    @NotNull(message = "{Categorie.notnull}")
    @Size(min=1, message = "{Categorie.notempty}")
    private String categorie;

    @NotNull(message = "{Nom.notnull}")
    @Size(min=1, message = "{Nom.notempty}")
    private String nom;

    private MultipartFile image;

    //Infos sur le client
    @NotNull(message = "{PrixAchat.notnull}")
    @Size(min=1, message = "{PrixAchat.notempty}")
    private String prixAchat;

    @NotNull(message = "{Prix.notnull}")
    @Size(min=1, message = "{Prix.notempty}")
    private String prix;

    @NotNull(message = "{Fournisseur.notnull}")
    @Size(min=1, message = "{Fournisseur.notempty}")
    private String fournisseur;
}