package com.example.csit950.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

    private String customer_id;

    private String customer_name;

    private String vip_active;

    private String vip_expire;

    private String address_id;

    public Customer(String customer_id, String customer_name, String vip_active, String vip_expire, String address_id) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.vip_active = vip_active;
        this.vip_expire = vip_expire;
        this.address_id = address_id;
    }

}
