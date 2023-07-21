package com.masai.service;

import java.util.List;
import com.masai.model.Customer;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer);
	

    public Customer updateCustomer(Integer id, Customer customer);

 
    public Customer removeCustomer(Integer customerId);


    public Customer viewCustomerById(Integer customerId);

   
    public List<Customer> viewAllCustomers();
}
