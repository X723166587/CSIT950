package com.example.csit950.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Restaurant {

    private int restaurant_id;

    private String restaurant_name;

    private String restaurant_category;

    private int restaurant_rating;

    private int restaurant_revenue;

    private String restaurant_address;

    private int restaurant_phone;

    private String restaurant_hero_image;

    public Restaurant (int restaurant_id, String restaurant_name,String restaurant_category, int restaurant_rating, int restaurant_revenue, String restaurant_address, int restaurant_phone, String restaurant_hero_image){
        this.restaurant_id = restaurant_id;
        this.restaurant_name = restaurant_name;
        this.restaurant_category = restaurant_category;
        this.restaurant_rating = restaurant_rating;
        this.restaurant_revenue = restaurant_revenue;
        this.restaurant_address = restaurant_address;
        this.restaurant_phone = restaurant_phone;
        this.restaurant_hero_image = restaurant_hero_image;
    }
}


