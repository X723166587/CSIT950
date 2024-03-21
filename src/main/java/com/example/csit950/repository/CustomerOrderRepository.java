package com.example.csit950.repository;

import com.example.csit950.model.Order;

import java.util.List;


public interface CustomerOrderRepository {
    Order save(Order order);

    Order findOne(String customer_id);

    void updateOrderStatus(String order_id, String newStatus);
    List<Order> findOrdersByRestaurantId(String restaurant_id);

    void updateOrderReview(String order_id, String newReview);
}
