package com.example.csit950.controller;

import com.example.csit950.model.Order;
import com.example.csit950.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ResponseBody
    public Order findOne(@RequestParam("order_id") String order_id) {
        return orderRepo.findOne(order_id);
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateOrderStatus(@RequestParam("order_id") String order_id, @RequestBody String newStatus) {
        try {
            orderRepo.updateOrderStatus(order_id, newStatus);
            return ResponseEntity.ok().body("Order status updated successfully.");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("Failed to update order status: " + e.getMessage());
        }
    }

}