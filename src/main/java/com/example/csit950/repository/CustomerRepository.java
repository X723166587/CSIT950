package com.example.csit950.repository;

import com.example.csit950.model.Customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    Customer findOne(String customer_id);

    Customer save(Customer customer);

}
