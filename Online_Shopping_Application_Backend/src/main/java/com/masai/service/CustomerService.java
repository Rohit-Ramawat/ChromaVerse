package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Add a new customer
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Update a customer
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    // Remove a customer
    public void removeCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }

    // View a customer by ID
    public Optional<Customer> viewCustomer(Integer customerId) {
        return customerRepository.findById(customerId);
    }

    // View all customers
    public List<Customer> viewAllCustomers() {
        return customerRepository.findAll();
    }
}

