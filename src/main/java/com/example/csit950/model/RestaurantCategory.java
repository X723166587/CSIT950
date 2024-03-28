package com.example.csit950.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RestaurantCategory {

    private int categoryId;
    private String categoryName;
    private String categoryIcon;
    private String categorySlug;

    public RestaurantCategory(int categoryId, String categoryName, String categoryIcon, String categorySlug) {
        this.categoryId =  categoryId;
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.categorySlug = categorySlug;
    }

    // Standard getters and setters
}

