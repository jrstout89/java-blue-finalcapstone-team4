package com.techelevator.dao;

import com.techelevator.model.Customers;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();
    Customers getCustomer(String username);
    //User profile page

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    Customers createCustomers (RegisterUserDto customer, int userId);

    public Customers getCustomerById(int customerId);

}
