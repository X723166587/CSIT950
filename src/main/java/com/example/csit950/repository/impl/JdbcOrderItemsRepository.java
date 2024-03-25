package com.example.csit950.repository.impl;

import com.example.csit950.model.OrderItem;
import com.example.csit950.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcOrderItemsRepository implements OrderItemsRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcOrderItemsRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insertOrderItems(List<OrderItem> orderItems) {
        String sql = "INSERT INTO OrderItems (order_id, item_id, quantity) VALUES (?, ?, ?)";
        for (OrderItem item : orderItems) {
            jdbc.update(sql, item.getOrderId(), item.getItemId(), item.getQuantity());
        }
    }

    @Override
    public List<OrderItem> findOrderItemsByOrderId(int orderId) {
        String sql = "SELECT order_id, item_id, quantity FROM OrderItems WHERE order_id = ?";
        return jdbc.query(sql, new Object[]{orderId}, (rs, rowNum) -> new OrderItem(
                rs.getInt("order_id"),
                rs.getInt("item_id"),
                rs.getInt("quantity")
        ));
    }
}
