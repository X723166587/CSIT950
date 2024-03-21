package com.example.csit950.repository.impl;

import com.example.csit950.model.Customer;
import com.example.csit950.model.Restaurant;
import com.example.csit950.repository.RestaurantRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRestaurantRepository implements RestaurantRepository {
    private JdbcTemplate jdbc;

    public JdbcRestaurantRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc ;
    }

    @Override
    public List<Restaurant> findRestaurant() {
        return jdbc.query("SELECT restaurant_id,restaurant_name,restaurant_category,restaurant_rating,restaurant_revenue,restaurant_address,restaurant_phone,restaurant_hero_image FROM Restaurant", this::mapRowToRestaurant);
    }

    private Restaurant mapRowToRestaurant(ResultSet rs, int rowNum) throws SQLException {
        return new Restaurant(
                rs.getInt("restaurant_id"), // This will now work for both methods.
                rs.getString("restaurant_name"),
                rs.getString("restaurant_category"),
                rs.getInt("restaurant_rating"),
                rs.getInt("restaurant_revenue"),
                rs.getString("restaurant_address"),
                rs.getInt("restaurant_phone"),
                rs.getString("restaurant_hero_image")
        );
    }

    @Override
    public Restaurant findRestaurantById(int restaurant_id) {
        String sql = "SELECT * FROM Restaurant WHERE restaurant_id = ?";
        return jdbc.queryForObject(sql, this::mapRowToRestaurant, restaurant_id);
    }


}
