package com.example.ist412group4.service;

import com.example.ist412group4.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    void deleteCustomerById(long id);
    Customer getCustomerByLogin(Customer customer);

    boolean validate(Customer customer);
}
