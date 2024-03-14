package com.example.csit950.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

    private String customer_id;

    private String customer_name;

    private String vip_status;

    private String vip_expire;

    private String customer_address;

    private String customer_phone;

    public Customer ( String customer_id, String customer_name, String vip_status, String vip_expire, String customer_address, String customer_phone) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.vip_status = vip_status;
        this.vip_expire = vip_expire;
        this.customer_address = customer_address;
        this.customer_phone = customer_phone;
    }

}
