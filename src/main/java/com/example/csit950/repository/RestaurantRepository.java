package com.example.csit950.repository;

import com.example.csit950.model.Customer;
import com.example.csit950.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> findRestaurant();
}