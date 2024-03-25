package com.example.csit950.controller;

import com.example.csit950.model.OrderItem;
import com.example.csit950.repository.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitems")
public class OrderItemsController {

    private final OrderItemsRepository repository;

    @Autowired
    public OrderItemsController(OrderItemsRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> addOrderItems(@RequestBody List<OrderItem> orderItems) {
        repository.insertOrderItems(orderItems);
        return ResponseEntity.ok("Order items added successfully.");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable int orderId) {
        List<OrderItem> items = repository.findOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }
}
