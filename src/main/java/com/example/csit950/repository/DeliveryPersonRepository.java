package com.example.csit950.repository;


import com.example.csit950.model.DeliveryPerson;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPersonRepository {
        Optional<DeliveryPerson> deliveryPersonLogin(String deliveryPersonName, String password);

}
