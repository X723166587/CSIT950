package com.example.csit950.customer;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

    private String enumber;

    private String name;

    private String dob;

    private String address;

    private String sex;

    public Customer(String enumber, String name, String dob, String address, String sex) {
        this.enumber = enumber;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.sex = sex;
    }

    public Customer(String name, String dob, String address, String sex) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.sex = sex;
    }
}
