package com.example.csit950.repository;

import com.example.csit950.model.Order;


public interface CustomerOrderRepository {
    Order save(Order order);
}
