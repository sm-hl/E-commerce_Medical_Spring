package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUsernameForm implements Serializable {
    
    //Infos sur le compte
    @NotNull(message = "{Username.notnull}")
    @Size(min=1, message = "{Username.notempty}")
    private String username;

    @NotNull(message = "{Password.notnull}")
    @Size(min=1, message = "{Password.notempty}")
    private String password;
}