package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class supprimerFournisseursForm implements Serializable {
    
    @NotNull()
    @Size(min=1)
    private String id;
}