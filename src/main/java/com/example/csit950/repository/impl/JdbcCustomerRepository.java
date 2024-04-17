package com.example.csit950.repository.impl;

import com.example.csit950.model.Customer;
import com.example.csit950.model.DeliveryPerson;
import com.example.csit950.repository.CustomerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcCustomerRepository implements CustomerRepository {

    private JdbcTemplate jdbc;

    public JdbcCustomerRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc ;
    }

    @Override
    public List<Customer> findAll() {
        return jdbc.query("SELECT customer_id,customer_name,vip_status,vip_expire,customer_address,customer_phone,password FROM Customer", new CustomerMapper());
    }

    @Override
    public Customer findOne(String customer_id) {
        // Adjusted to also select customer_id, even though it's known, for consistent mapping.
        Customer customer = jdbc.queryForObject("SELECT customer_id,customer_name,vip_status,vip_expire,customer_address,customer_phone,password FROM Customer WHERE customer_id = ?", new CustomerMapper(), customer_id);
        return customer;
    }


    private static final class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Customer(
                    rs.getInt("customer_id"), // This will now work for both methods.
                    rs.getString("customer_name"),
                    rs.getString("vip_status"),
                    rs.getString("vip_expire"),
                    rs.getString("customer_address"),
                    rs.getString("customer_phone"),
                    rs.getString("password"));
        }

    }

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO Customer (customer_name, vip_status, vip_expire, customer_address, customer_phone,password) VALUES (?, ?, ?, ?, ?,?)";
        jdbc.update(sql, customer.getCustomer_name(), customer.getVip_status(), customer.getVip_expire(), customer.getCustomer_address(), customer.getCustomer_phone(),customer.getPassword());
        return customer;
    }

    @Override
    public Optional<Customer> findCustomerByNameAndPassword(String customer_name, String password) {
        String sql = "SELECT * FROM Customer WHERE customer_name = ? AND password = ?";
        try {
            Customer customer = jdbc.queryForObject(sql, new CustomerMapper(), customer_name, password);
            return Optional.ofNullable(customer);
        } catch (Exception e) {
            // Log exception details here, if necessary
            return Optional.empty();
        }
    }

}
