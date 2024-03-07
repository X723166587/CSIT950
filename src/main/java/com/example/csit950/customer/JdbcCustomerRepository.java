package com.example.csit950.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private JdbcTemplate jdbc;

    public JdbcCustomerRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc ;
    }

    @Override
    public List<Customer> findAll() {
        return jdbc.query("SELECT enumber,name,dob,address,sex from COMPUTING", this::mapRowToCustomer);
    }

    @Override
    public Customer findOne(String enumber) {
        Customer customer = jdbc.queryForObject("select name,dob,address,sex from COMPUTING where enumber = ?", this::mapRowToCustomer1,enumber);
        return customer;
    }


    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getString("enumber"),
                rs.getString("name"),
                rs.getString("dob"),
                rs.getString("address"),
                rs.getString("sex"));
    }

    private Customer mapRowToCustomer1(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
                rs.getString("name"),
                rs.getString("dob"),
                rs.getString("address"),
                rs.getString("sex"));
    }

}
