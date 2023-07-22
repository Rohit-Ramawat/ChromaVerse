package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.service.CustomerService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		System.out.println(auth); // this Authentication object having Principle object details
		
		Customer customer = customerService.getCustomerDetailsByEmail(auth.getName());
		 
		return new ResponseEntity<>(customer.getFirstName()+" logged in successfully", HttpStatus.ACCEPTED);		
	}
}
