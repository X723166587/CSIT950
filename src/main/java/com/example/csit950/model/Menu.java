package com.example.csit950.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Menu {
    private int item_id;

    private String item_name;

    private double item_price;

    private String item_image;

    private String item_description;

    public Menu(int item_id, String item_name, double item_price, String item_image, String item_description) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_image = item_image;
        this.item_description = item_description;
    }
}
