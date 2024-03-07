package com.example.csit950.customer;

import java.util.List;

public interface CustomerRepository {
    List<Customer> findAll();

    Customer findOne(String enumber);



}
