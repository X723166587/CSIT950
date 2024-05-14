package com.example.csit950.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Order {
    private String order_id;

    private Integer restaurant_id;

    private String customer_id;


    private String order_status;

    private final String comment;

    private String order_subtotal;

    private String order_rating;


    private String order_review;

    private String order_service_fee;

    private LocalDateTime create_time;


    public Order(String order_id, int restaurant_id, String customer_id, String order_status, String comment,String order_subtotal, String order_rating, String order_review, String order_service_fee) {
        this.order_id = order_id;
        this.restaurant_id = restaurant_id;
        this.customer_id = customer_id;
        this.order_status = order_status;
        this.comment = comment;
        this.order_subtotal = order_subtotal;
        this.order_rating = order_rating;
        this.order_review = order_review;
        this.order_service_fee = order_service_fee;
        this.create_time = LocalDateTime.now();
    }

}
