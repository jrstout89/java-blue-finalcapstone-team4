package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Customers;
import com.techelevator.model.RegisterUserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;

@Component
public class JdbcUserDao implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUser(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public Customers getCustomerById(int customerId) {
        Customers customers = null;
        String sql = "SELECT customer_id, phone_number, email, first_name, last_name, user_id FROM customers WHERE customer_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customerId);
            if (results.next()) {
                customers = mapRowToCustomers(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash, role FROM users";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                User user = mapRowToUser(results);
                users.add(user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }
    @Override
    //User profile page
    public Customers getCustomer(String username){
        Customers customers = null;
        String sql = "select customers.customer_id,customers.phone_number,customers.email,customers.first_name,customers.last_name,customers.user_id from customers " +
                "join users on customers.user_id = users.user_id  " +
                "where username = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
            if (results.next()){
                customers = mapRowToCustomers(results);
            }
        }  catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return customers;
    }
    @Override
    public User getUserByUsername(String username) {
        if (username == null) throw new IllegalArgumentException("Username cannot be null");
        User user = null;
        String sql = "SELECT user_id, username, password_hash, role FROM users WHERE username = LOWER(TRIM(?));";
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public User createUser(RegisterUserDto user) {

        User newUser = null;
        String insertUserSql = "INSERT INTO users (username, password_hash, role) values (LOWER(TRIM(?)), ?, ?) RETURNING user_id";

        String password_hash = new BCryptPasswordEncoder().encode(user.getPassword());
        String ssRole = user.getRole().toUpperCase().startsWith("ROLE_") ? user.getRole().toUpperCase() : "ROLE_" + user.getRole().toUpperCase();

        try {
            int newUserId = jdbcTemplate.queryForObject(insertUserSql, int.class, user.getUsername(), password_hash, ssRole);

            newUser = getUserById(newUserId);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newUser;
    }

    @Override
    public Customers createCustomers (RegisterUserDto customer, int userId) {

        Customers newCustomer = null;
        String insertCustomerSql = "INSERT INTO customers (phone_number, email, first_name, last_name, user_id) values (LOWER(TRIM(?)), ?, ?, ?, ?) RETURNING customer_id";

        try {
            int newCustomerId = jdbcTemplate.queryForObject(insertCustomerSql, int.class, customer.getPhoneNumber(), customer.getEmail(), customer.getFirstName(), customer.getLastName(), userId);

            newCustomer = getCustomerById(newCustomerId);


        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newCustomer;

    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setAuthorities(Objects.requireNonNull(rs.getString("role")));
        user.setActivated(true);
        return user;
    }

    private Customers mapRowToCustomers(SqlRowSet rs) {
        Customers customers = new Customers();
        customers.setId(rs.getInt("customer_id"));
        customers.setPhoneNumber(rs.getString("phone_number"));
        customers.setEmail(rs.getString("email"));
        customers.setFirstName(rs.getString("first_name"));
        customers.setLastName(rs.getString("last_name"));
        customers.setUserId(rs.getInt("user_id"));
        return customers;
    }
}
