package com.masai.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.masai.model.Customer;
import com.masai.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@RestController
public class CustomerController {
	
	@Autowired
    private CustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
    	
    	Customer savedCustomer = customerService.addCustomer(customer);
        
        return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
        
    }

    @SecurityRequirement(name = "demo-openapi")
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, 
    												@Valid @RequestBody Customer customer) {
        
    	Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        
        return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
    }

    @SecurityRequirement(name = "demo-openapi")
    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Customer> removeCustomer(@PathVariable Integer customerId) {
    	
    	Customer deletedCustomer = customerService.removeCustomer(customerId);
        
        return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
    }

    @SecurityRequirement(name = "demo-openapi")
    @GetMapping("/customers/{customerId}")
    public ResponseEntity<Customer> viewCustomer(@PathVariable Integer customerId) {
    	
    	Customer fetchedCustomer = customerService.viewCustomerById(customerId);
    	
    	return new ResponseEntity<Customer>(fetchedCustomer, HttpStatus.OK);
    }

    @SecurityRequirement(name = "demo-openapi")
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> viewAllCustomers() {
    	
        List<Customer> customers = customerService.viewAllCustomers();
        
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);  
    }
    
    @SecurityRequirement(name = "demo-openapi")
    @PostMapping("/admins")
    public ResponseEntity<Customer> addAdminHandler(@Valid @RequestBody Customer admin) {
    	
    	Customer savedAdmin = customerService.addAdmin(admin);
        
        return new ResponseEntity<Customer>(savedAdmin, HttpStatus.CREATED);
        
    }
}

