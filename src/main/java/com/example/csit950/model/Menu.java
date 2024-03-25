package com.example.csit950.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Menu {
    private int item_id;

    private String item_name;

    private int restaurant_id;

    private double item_price;

    private String item_image;

    private String item_description;

    private String item_category;

    public Menu(int item_id, String item_name, int restaurant_id,double item_price, String item_image, String item_description, String item_category) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.restaurant_id = restaurant_id;
        this.item_price = item_price;
        this.item_image = item_image;
        this.item_description = item_description;
        this.item_category = item_category;
    }
}
