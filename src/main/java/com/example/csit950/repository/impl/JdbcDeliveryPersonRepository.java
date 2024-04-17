package com.example.csit950.repository.impl;

import com.example.csit950.model.DeliveryPerson;
import com.example.csit950.repository.DeliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


@Repository
public class JdbcDeliveryPersonRepository implements DeliveryPersonRepository {
    private JdbcTemplate jdbc;
    @Autowired
    public JdbcDeliveryPersonRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    private static final class DeliveryPersonMapper implements RowMapper<DeliveryPerson> {
        @Override
        public DeliveryPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DeliveryPerson(
                    rs.getInt("delivery_person_id"),
                    rs.getString("delivery_person_name"),
                    rs.getString("delivery_person_phone_number"),
                    rs.getString("password")
            );
        }
    }

    @Override
    public Optional<DeliveryPerson> deliveryPersonLogin(String deliveryPersonName, String password) {
        String sql = "SELECT * FROM DeliveryPerson WHERE delivery_person_name = ? AND password = ?";
        try {
            DeliveryPerson deliveryPerson = jdbc.queryForObject(sql, new DeliveryPersonMapper(), deliveryPersonName, password);
            return Optional.ofNullable(deliveryPerson);
        } catch (Exception e) {
            // Log the exception as needed
            return Optional.empty();
        }
    }
}

