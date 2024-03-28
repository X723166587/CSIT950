package com.example.csit950.repository.impl;

import com.example.csit950.model.RestaurantCategory;
import com.example.csit950.repository.RestaurantCategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRestaurantCategoryRepository implements RestaurantCategoryRepository {

    private final JdbcTemplate jdbc;

    public JdbcRestaurantCategoryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public RestaurantCategory findOne(int restaurantId) {
        String sql = """
                     SELECT rc.category_id, rc.category_name, rc.category_icon, rc.category_slug 
                     FROM RestaurantCategory rc
                     JOIN Restaurant r ON rc.category_id = r.category_id
                     WHERE r.restaurant_id = ?
                     """;
        return jdbc.queryForObject(sql, this::mapRowToRestaurantCategory, restaurantId);
    }

    private RestaurantCategory mapRowToRestaurantCategory(ResultSet rs, int rowNum) throws SQLException {
        return new RestaurantCategory(
                rs.getInt("category_id"),
                rs.getString("category_name"),
                rs.getString("category_icon"),
                rs.getString("category_slug")
        );
    }

@Override
public RestaurantCategory save(RestaurantCategory category) {
   String sql = "INSERT INTO RestaurantCategory (category_name, category_icon, category_slug) VALUES (?, ?, ?)";
   jdbc.update(sql, category.getCategoryName(), category.getCategoryIcon(), category.getCategorySlug());
   return category;  // This needs to be improved to handle the generated key
}
}
