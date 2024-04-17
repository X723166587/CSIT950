package com.example.csit950.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DeliveryPerson {
    private Integer delivery_person_id;

    private String delivery_person_name;

    private String delivery_person_phone_number;

    private String password;

    public DeliveryPerson(int delivery_person_id, String delivery_person_name, String delivery_person_phone_number,String password) {
        this.delivery_person_id = delivery_person_id;
        this.delivery_person_name = delivery_person_name;
        this.delivery_person_phone_number = delivery_person_phone_number;
        this.password = password;
    }
}


