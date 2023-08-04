package com.miniprojet.miniprojet.Model.Form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaypalForm implements Serializable {

    @NotNull()
    @Size(min=1)
    private String product;
    @NotNull()
    @Size(min=1)
    private String subtotal;
    @NotNull()
    @Size(min=1)
    private String shipping;
    @NotNull()
    @Size(min=1)
    private String tax;
    @NotNull()
    @Size(min=1)
    private String total;
}