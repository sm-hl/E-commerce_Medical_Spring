package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import lombok.Data;

import java.io.Serializable;

@Data
public class ForgotPasswordForm implements Serializable {

    @NotNull(message = "{Email.notnull}")
    @Size(min=1, message = "{Email.notempty}")
    @Email(message = "{Email.notvalid}")
    private String email;
}