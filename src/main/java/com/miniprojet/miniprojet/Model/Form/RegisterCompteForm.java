package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterCompteForm implements Serializable {
    
    //Infos sur le compte
    @NotNull(message = "{Username.notnull}")
    @Size(min=1, message = "{Username.notempty}")
    private String username;

    @NotNull(message = "{Email.notnull}")
    @Size(min=1, message = "{Email.notempty}")
    @Email(message = "{Email.notvalid}")
    private String email;

    @NotNull(message = "{Password.notnull}")
    @Size(min=1, message = "{Password.notempty}")
    private String password;

    //Infos sur le client
    @NotNull(message = "{Adresse.notnull}")
    @Size(min=1, message = "{Adresse.notempty}")
    private String adresse;

    @NotNull(message = "{NomComplet.notnull}")
    @Size(min=1, message = "{NomComplet.notempty}")
    private String nomComplet;

    @NotNull(message = "{Pays.notnull}")
    @Size(min=1, message = "{Pays.notempty}")
    private String pays;

    @NotNull(message = "{Province.notnull}")
    @Size(min=1, message = "{Province.notempty}")
    private String province;

    @NotNull(message = "{Tel.notnull}")
    @Size(min=1, message = "{Tel.notempty}")
    @Pattern(regexp = "^\\d{10}$", message = "{Tel.invalid}")
    private String tel;
}