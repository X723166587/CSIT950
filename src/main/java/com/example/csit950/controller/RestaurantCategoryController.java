package com.example.csit950.controller;

import com.example.csit950.model.RestaurantCategory;
import com.example.csit950.repository.impl.JdbcRestaurantCategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class RestaurantCategoryController {

    private final JdbcRestaurantCategoryRepository categoryRepo;

    public RestaurantCategoryController(JdbcRestaurantCategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/{restaurantId}/category")
    public ResponseEntity<RestaurantCategory> getCategoryByRestaurant(@PathVariable int restaurantId) {
        try {
            RestaurantCategory category = categoryRepo.findOne(restaurantId);
            return ResponseEntity.ok(category);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RestaurantCategory> createCategory(@RequestBody RestaurantCategory category) {
        RestaurantCategory savedCategory = categoryRepo.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
}
