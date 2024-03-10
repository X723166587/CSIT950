package com.example.csit950.controller;

import com.example.csit950.model.Order;
import com.example.csit950.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CustomerOrderRepository orderRepo;

    @Autowired // Explicitly marking constructor for autowiring, though optional in single-constructor scenarios
    public OrderController(CustomerOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepo.save(order);
        return ResponseEntity.ok(savedOrder);
    }
}