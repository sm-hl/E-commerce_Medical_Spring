package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResetPasswordForm implements Serializable {

    @NotNull()
    @Size(max=255)
    private String token;

    @NotNull(message = "{Password.notnull}")
    @Size(min=1, message = "{Password.notempty}")
    private String password;
}