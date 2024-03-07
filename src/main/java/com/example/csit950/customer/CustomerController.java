package com.example.csit950.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepo;

    public CustomerController(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @GetMapping
    @ResponseBody
    public Customer findOne(@RequestParam("enumber")String enumber){
        return customerRepo.findOne(enumber);
    }

    @GetMapping("all")
    @ResponseBody
    public List<Customer> findAll(){
        return customerRepo.findAll();
    }
}



