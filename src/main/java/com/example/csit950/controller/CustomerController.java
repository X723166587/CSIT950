package com.example.csit950.controller;

import com.example.csit950.model.Customer;
import com.example.csit950.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepo;

    public CustomerController(CustomerRepository customerRepo) {

        this.customerRepo = customerRepo;
    }

    @GetMapping
    @ResponseBody
    public Customer findOne(@RequestParam("customer_id")String customer_id){
        return customerRepo.findOne(customer_id);
    }

    @GetMapping("all")
    @ResponseBody
    public List<Customer> findAll(){
        return customerRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerRepo.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestParam String customer_name, @RequestParam String password) {
        Optional<Customer> customer = customerRepo.findCustomerByNameAndPassword(customer_name, password);
        if (customer.isPresent()) {
            return ResponseEntity.ok().body("Customer login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}



