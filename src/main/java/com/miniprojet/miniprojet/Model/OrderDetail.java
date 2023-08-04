package com.miniprojet.miniprojet.Model;

import java.util.List;

import lombok.Data;

@Data
public class OrderDetail {
    private List<Panier> panier;
    private float total;
}
