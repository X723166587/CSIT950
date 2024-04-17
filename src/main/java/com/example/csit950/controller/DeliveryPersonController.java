package com.example.csit950.controller;

import com.example.csit950.model.DeliveryPerson;
import com.example.csit950.repository.DeliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/delivery-person")
public class DeliveryPersonController {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public DeliveryPersonController(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String deliveryPersonName, @RequestParam String password) {
        Optional<DeliveryPerson> deliveryPerson = deliveryPersonRepository.deliveryPersonLogin(deliveryPersonName, password);
        if (deliveryPerson.isPresent()) {
            // Consider returning a secured DTO or JWT token instead of sensitive data
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}






