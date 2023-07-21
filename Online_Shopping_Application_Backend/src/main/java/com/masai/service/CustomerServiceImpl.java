package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
    private CustomerRepository customerRepository;

    // Add a new customer
    public Customer addCustomer(Customer customer) {
    	
    	if(customer == null) throw new CustomerException("Null details are not allowed");
    	
    	Optional<Customer> opt = customerRepository.findByEmail(customer.getEmail());
    	 
    	if(opt.isPresent()) throw new CustomerException("Customer with this email is already present");
    	
    	customer.setRole("ROLE_USER");
    	
    	Cart cart = new Cart();
    	
    	customer.setCart(cart);
    	
    	cart.setCustomer(customer);
    	
        return customerRepository.save(customer);
    }

    // Update a customer
    public Customer updateCustomer(Integer id, Customer customer) {
    	
    	if(customer == null) throw new CustomerException("Null details are not allowed");
    	
    	Customer savedCustomer = customerRepository.findById(id)
    			.orElseThrow(() -> new CustomerException("Invalid Customer id"));
    	
    	savedCustomer.setFirstName(customer.getFirstName());
    	savedCustomer.setLastName(customer.getLastName());
    	savedCustomer.setMobileNumber(customer.getMobileNumber());
    	savedCustomer.setAddress(customer.getAddress());
    	
        return customerRepository.save(savedCustomer);
        
    }

    // Remove a customer
    public Customer removeCustomer(Integer customerId) {
    	
    	Customer savedCustomer = customerRepository.findById(customerId)
		.orElseThrow(() -> new CustomerException("Invalid Customer id"));
    	
        customerRepository.deleteById(customerId);
        
        return savedCustomer;
        
    }

    // View a customer by ID
    public Customer viewCustomerById(Integer customerId) {
    	
    	Customer savedCustomer = customerRepository.findById(customerId)
    			.orElseThrow(() -> new CustomerException("Invalid Customer id"));
  
    	return savedCustomer;
    }

    // View all customers
    public List<Customer> viewAllCustomers() {
    	
        return customerRepository.findAll();
        
    }
    
}

