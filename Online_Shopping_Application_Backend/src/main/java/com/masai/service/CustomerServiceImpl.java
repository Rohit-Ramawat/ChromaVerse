package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    // Add a new customer
    public Customer addCustomer(Customer customer) {
    	
    	if(customer == null) throw new CustomerException("Null details are not allowed");
    	
    	Optional<Customer> opt = customerRepository.findByEmail(customer.getEmail());
    	 
    	if(opt.isPresent()) throw new CustomerException("Customer with this email is already present");
    	
    	customer.setRole("ROLE_USER");                                          //assigning role as a user
    	
    	customer.setPassword(passwordEncoder.encode(customer.getPassword()));  //encoding password
    	
    	Cart cart = new Cart();                                               //assigning cart
    	
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

    
    //Adding a new admin
	@Override
	public Customer addAdmin(Customer admin) {
		
		if(admin == null) throw new CustomerException("Null details are not allowed");
    	
    	Optional<Customer> opt = customerRepository.findByEmail(admin.getEmail());
    	 
    	if(opt.isPresent()) throw new CustomerException("Admin with this email is already present");
    	
    	admin.setRole("ROLE_ADMIN");                                     //assigning role as a admin
    	
    	admin.setPassword(passwordEncoder.encode(admin.getPassword()));  //encoding password
    	
		return customerRepository.save(admin);
	}

	
	//for fetching customer details by email
	@Override
	public Customer getCustomerDetailsByEmail(String email) {
		
		return customerRepository.findByEmail(email)
				.orElseThrow(() -> new CustomerException("Customer Not found with Email: "+email));
		
	}
    
    
}

