package com.example.csit950.controller;

import com.example.csit950.model.Order;
import com.example.csit950.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/restaurant/{restaurantId}")
    @ResponseBody
    public List<Order> findOrdersByRestaurantId(@PathVariable("restaurantId") String restaurant_id) {
        return orderRepo.findOrdersByRestaurantId(restaurant_id);
    }

    @PatchMapping("/{orderId}/review")
    public ResponseEntity<?> updateOrderReview(@PathVariable("orderId") String order_id, @RequestBody String newReview) {
        try {
            orderRepo.updateOrderReview(order_id, newReview);
            return ResponseEntity.ok().body("Order review updated successfully.");
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("Failed to update order review: " + e.getMessage());
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> findOrdersByCustomerId(@PathVariable("customerId") String customer_id) {
        try {
            List<Order> orders = orderRepo.findOrdersByCustomerId(customer_id);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // Consider a more descriptive error message or handling
        }
    }
}