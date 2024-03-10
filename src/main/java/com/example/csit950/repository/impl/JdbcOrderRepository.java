package com.example.csit950.repository.impl;

import com.example.csit950.model.Order;
import com.example.csit950.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements CustomerOrderRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public void JdbcCustomerOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("CustomerOrder")
                .usingGeneratedKeyColumns("order_id");
    }

    @Override
    public Order save(Order order) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("restaurant_id", order.getRestaurant_id());
        parameters.put("customer_id", order.getCustomer_id());
        parameters.put("address_id", order.getAddress_id());
        parameters.put("order_status", order.getOrder_status());
        parameters.put("order_price", order.getOrder_price());

        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        order.setOrder_id(newId.toString());
        return order;
    }


}
