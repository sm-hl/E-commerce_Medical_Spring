package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class modifierStocksForm implements Serializable {
    
    //Infos sur le compte
    @NotNull(message = "{Stock.notnull}")
    @Size(min=1, message = "{Stock.notempty}")
    private String idStock;

    @NotNull(message = "{Nb.notnull}")
    @Size(min=1, message = "{Nb.notempty}")
    private String nbStock;
}