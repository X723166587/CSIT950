package com.example.csit950.controller;

import com.example.csit950.model.Customer;
import com.example.csit950.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
}



