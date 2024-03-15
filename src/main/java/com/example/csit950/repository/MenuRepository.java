package com.example.csit950.repository;

import com.example.csit950.model.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> selectRestaurant(int restaurant_id);
}
