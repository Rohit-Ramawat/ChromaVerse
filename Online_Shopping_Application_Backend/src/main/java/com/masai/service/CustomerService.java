package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.model.Customer;

public interface CustomerService {
	
	public void addCustomer(Customer customer);
	

    public void updateCustomer(Customer customer);

 
    public void removeCustomer(Integer customerId);


    public Optional<Customer> viewCustomer(Integer customerId);

   
    public List<Customer> viewAllCustomers();
}
