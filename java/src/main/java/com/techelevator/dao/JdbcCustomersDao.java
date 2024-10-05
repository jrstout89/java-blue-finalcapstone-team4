package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCustomersDao implements CustomersDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getCustomerIdByUsername(String username) {
        String sql = "SELECT customers.customer_id FROM customers JOIN users on customers.user_id = users.user_id WHERE users.username = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        if (results.next()) {
            return results.getInt("customer_id");
        }
        throw new RuntimeException("Customer not found for username: " + username);
    }
}
