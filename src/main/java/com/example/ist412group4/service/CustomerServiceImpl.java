package com.example.ist412group4.service;

import java.util.List;
import java.util.Optional;
import com.example.ist412group4.model.Customer;
import com.example.ist412group4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException("Customer not found for id :: " + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(long id) {
        this.customerRepository.deleteById(id);
    }
    @Override
    public boolean validate(Customer cust1) {
        for (Customer c : this.customerRepository.findAll()) {
            if (cust1.getCustEmail().equals(c.getCustEmail()) && (cust1.getCustPassword().equals(c.getCustPassword()))) {
                return true;
            }
        } return false;
    }
}
