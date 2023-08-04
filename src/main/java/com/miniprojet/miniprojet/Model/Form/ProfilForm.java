package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfilForm implements Serializable {

    //Infos sur le client
    @Size(min=1, message = "{Adresse.notempty}")
    private String adresse;

    @Size(min=1, message = "{NomComplet.notempty}")
    private String nomComplet;

    @Size(min=1, message = "{Pays.notempty}")
    private String pays;

    @Size(min=1, message = "{Province.notempty}")
    private String province;

    @Size(min=1, message = "{Tel.notempty}")
    @Pattern(regexp = "^\\d{10}$", message = "{Tel.invalid}")
    private String tel;
}