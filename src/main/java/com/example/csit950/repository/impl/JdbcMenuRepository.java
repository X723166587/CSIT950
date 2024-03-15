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
        return jdbc.query("select item_id,item_name,item_price,item_image,item_description from menu where restaurant_id = ?", this::mapRowToMenu, restaurant_id);
    };

    private Menu mapRowToMenu(ResultSet rs, int rowNum) throws SQLException {
        return new Menu(
                rs.getInt("item_id"),
                rs.getString("item_name"),
                rs.getDouble("item_price"), // Use getDouble for monetary values
                rs.getString("item_image"),
                rs.getString("item_description")
        );
    }
}
