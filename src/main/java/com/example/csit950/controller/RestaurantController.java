package com.example.csit950.controller;


import com.example.csit950.model.Customer;
import com.example.csit950.model.Order;
import com.example.csit950.model.Restaurant;
import com.example.csit950.repository.CustomerOrderRepository;
import com.example.csit950.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<?> updateRestaurant(@PathVariable int restaurantId, @RequestBody Restaurant restaurant) {
        restaurant.setRestaurant_id(restaurantId); // Ensure the ID is set correctly
        restaurantRepo.updateRestaurant(restaurant);
        return ResponseEntity.ok().body("Restaurant updated successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRestaurant(@RequestParam String restaurantName, @RequestParam String password) {
        Optional<Restaurant> restaurant = restaurantRepo.findRestaurantByNameAndPassword(restaurantName, password);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok().body("Restaurant login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
