package com.example.BaitapJava.Bai8.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long id;
    private String name;
    private double price;
    private int quantity;
}