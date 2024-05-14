package com.example.csit950.repository.impl;

import com.example.csit950.model.Customer;
import com.example.csit950.model.Order;
import com.example.csit950.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements CustomerOrderRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void JdbcCustomerOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("CustomerOrder")
                .usingGeneratedKeyColumns("order_id");
    }

    @Override
    public Order save(Order order) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("restaurant_id", order.getRestaurant_id());
        parameters.put("customer_id", order.getCustomer_id());
        parameters.put("order_status", order.getOrder_status());
        parameters.put("order_subtotal", order.getOrder_subtotal());
        parameters.put("order_rating", order.getOrder_rating());
        parameters.put("order_review", order.getOrder_review());
        parameters.put("order_service_fee", order.getOrder_service_fee());
        parameters.put("create_time", order.getCreate_time());

        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        order.setOrder_id(newId.toString());
        return order;
    }

    private Order mapRowToOrder(ResultSet rs, int rowNum) throws SQLException {
        return new Order(
                rs.getString("order_id"), // This will now work for both methods.
                rs.getInt("restaurant_id"),
                rs.getString("customer_id"),
                rs.getString("order_status"),
                rs.getString("comment"),
                rs.getString("order_subtotal"),
                rs.getString("order_rating"),
                rs.getString("order_review"),
                rs.getString("order_service_fee"));
    }

    @Override
    public List<Order> findOrdersByCustomerId(String customer_id) {
        String sql = "SELECT * FROM CustomerOrder WHERE customer_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToOrder, customer_id);
    }

    @Override
    public Order findOne(String order_id) {
        // Adjusted to also select customer_id, even though it's known, for consistent mapping.
        Order order = jdbcTemplate.queryForObject("SELECT order_id,restaurant_id,customer_id,order_status,comment,order_subtotal,order_rating,order_review,order_service_fee FROM CustomerOrder WHERE order_id = ?", this::mapRowToOrder, order_id);
        return order;
    }

    public void updateOrderStatus(String order_id, String newStatus) {
        String sql = "UPDATE CustomerOrder SET order_status = ? WHERE order_id = ?";
        System.out.println("Updating order status to: '" + newStatus + "'");
        jdbcTemplate.update(sql, newStatus, order_id);
    }

    @Override
    public List<Order> findOrdersByRestaurantId(String restaurant_id) {
        String sql = "SELECT * FROM CustomerOrder WHERE restaurant_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToOrder, restaurant_id);
    }

    @Override
    public void updateOrderReview(String order_id, String newReview) {
        String sql = "UPDATE CustomerOrder SET order_review = ? WHERE order_id = ?";
        jdbcTemplate.update(sql, newReview, order_id);
    }
}
