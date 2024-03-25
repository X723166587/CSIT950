package com.example.csit950.repository.impl;

import com.example.csit950.model.Menu;
import com.example.csit950.repository.MenuRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcMenuRepository implements MenuRepository {

    private JdbcTemplate jdbc;

    public JdbcMenuRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc ;
    }

    @Override
    public List<Menu> selectRestaurant(int restaurant_id){
        return jdbc.query("select item_id,item_name,restaurant_id,item_price,item_image,item_description,item_category from menu where restaurant_id = ?", this::mapRowToMenu, restaurant_id);
    };

    private Menu mapRowToMenu(ResultSet rs, int rowNum) throws SQLException {
        return new Menu(
                rs.getInt("item_id"),
                rs.getString("item_name"),
                rs.getInt("restaurant_id"),
                rs.getDouble("item_price"), // Use getDouble for monetary values
                rs.getString("item_image"),
                rs.getString("item_description"),
                rs.getString("item_category")
        );
    }

    @Override
    public void updateMenu(Menu menu) {
        String sql = "UPDATE Menu SET item_name = ?, item_price = ?, item_image = ?, item_description = ?, item_category = ? WHERE item_id = ?";
        jdbc.update(sql, menu.getItem_name(), menu.getItem_price(), menu.getItem_image(), menu.getItem_description(), menu.getItem_category(), menu.getItem_id()); // Notice item_category is now included in the parameters
    }

    @Override
    public void addMenu(Menu menu) {
        String sql = "INSERT INTO menu (item_name, restaurant_id, item_price, item_image, item_description, item_category) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql, menu.getItem_name(), menu.getRestaurant_id(), menu.getItem_price(), menu.getItem_image(), menu.getItem_description(), menu.getItem_category());
    }

    @Override
    public void deleteMenu(int menuId) {
        String sql = "DELETE FROM Menu WHERE item_id = ?";
        jdbc.update(sql, menuId);
    }
}
