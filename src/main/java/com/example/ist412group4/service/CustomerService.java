package com.example.ist412group4.service;

import com.example.ist412group4.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    void saveCustomer(Customer customer);
    Customer getCustomerById(long id);
    void deleteCustomerById(long id);

    boolean validate(Customer customer);
}
