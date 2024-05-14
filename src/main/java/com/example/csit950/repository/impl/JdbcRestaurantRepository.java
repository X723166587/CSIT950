package com.example.csit950.repository.impl;

import com.example.csit950.model.Customer;
import com.example.csit950.model.Restaurant;
import com.example.csit950.repository.RestaurantRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcRestaurantRepository implements RestaurantRepository {
    private JdbcTemplate jdbc;

    public JdbcRestaurantRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc ;
    }

    @Override
    public List<Restaurant> findRestaurant() {
        return jdbc.query("SELECT restaurant_id,restaurant_name,restaurant_rating,restaurant_revenue,restaurant_address,restaurant_phone,restaurant_hero_image,category_id,password,email FROM Restaurant", this::mapRowToRestaurant);
    }

    private Restaurant mapRowToRestaurant(ResultSet rs, int rowNum) throws SQLException {
        return new Restaurant(
                rs.getInt("restaurant_id"), // This will now work for both methods.
                rs.getString("restaurant_name"),
                rs.getInt("restaurant_rating"),
                rs.getInt("restaurant_revenue"),
                rs.getString("restaurant_address"),
                rs.getInt("restaurant_phone"),
                rs.getString("restaurant_hero_image"),
                rs.getInt("category_id"),
                rs.getString("password"),
                rs.getString("email")
        );
    }

    @Override
    public Restaurant findRestaurantById(int restaurant_id) {
        String sql = "SELECT * FROM Restaurant WHERE restaurant_id = ?";
        return jdbc.queryForObject(sql, this::mapRowToRestaurant, restaurant_id);
    }


    @Override
    public void updateRestaurant(Restaurant restaurant) {
        String sql = "UPDATE Restaurant SET restaurant_name = ?, restaurant_category = ?, restaurant_rating = ?, restaurant_revenue = ?, restaurant_address = ?, restaurant_phone = ?, restaurant_hero_image = ? WHERE restaurant_id = ?";
        jdbc.update(sql, restaurant.getRestaurant_name(), restaurant.getRestaurant_rating(), restaurant.getRestaurant_revenue(), restaurant.getRestaurant_address(), restaurant.getRestaurant_phone(), restaurant.getRestaurant_hero_image(), restaurant.getRestaurant_id(),restaurant.getCategory_id());
    }

    @Override
    public Optional<Restaurant> findRestaurantByNameAndPassword(String restaurantName, String password) {
        String sql = "SELECT * FROM Restaurant WHERE restaurant_name = ? AND password = ?";
        try {
            Restaurant restaurant = jdbc.queryForObject(sql, new Object[]{restaurantName, password}, this::mapRowToRestaurant);
            return Optional.ofNullable(restaurant);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}
