package com.example.csit950.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Order {
    private String order_id;

    private String restaurant_id;

    private String customer_id;

    private String address_id;

    private String order_status;

    private String order_price;

    public Order(String order_id, String restaurant_id, String customer_id, String address_id, String order_status, String order_price) {
        this.order_id = order_id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.address_id = address_id;
        this.order_status = order_status;
        this.order_price = order_price;
    }

}
