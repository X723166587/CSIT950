package com.example.csit950.repository;

import com.example.csit950.model.OrderItem;

import java.util.List;

public interface OrderItemsRepository {

    void insertOrderItems(List<OrderItem> orderItems);

    List<OrderItem> findOrderItemsByOrderId(int orderId);
}

