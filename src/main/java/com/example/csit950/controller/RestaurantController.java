package com.example.csit950.controller;


import com.example.csit950.model.Customer;
import com.example.csit950.model.Order;
import com.example.csit950.model.Restaurant;
import com.example.csit950.repository.CustomerOrderRepository;
import com.example.csit950.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantRepository restaurantRepo;

    @Autowired
    public RestaurantController(RestaurantRepository restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    @GetMapping("all")
    @ResponseBody
    public List<Restaurant> findRestaurant(){
        return restaurantRepo.findRestaurant();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Restaurant findRestaurantById(@PathVariable("id") Integer restaurant_id) {
        return restaurantRepo.findRestaurantById(restaurant_id);
    }
}
