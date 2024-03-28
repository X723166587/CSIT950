package com.example.csit950.repository;

import com.example.csit950.model.Restaurant;
import com.example.csit950.model.RestaurantCategory;

import java.util.List;

public interface RestaurantCategoryRepository {
    RestaurantCategory findOne(int categoryId);
    RestaurantCategory save(RestaurantCategory category);
}

