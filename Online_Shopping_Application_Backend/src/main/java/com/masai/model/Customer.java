package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Customer extends User{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@NotBlank(message = "first name can not be blank")
	@Size(min = 2,max = 50,message = "please provide a valid")
	private String firstName;
	
	@NotBlank(message = "last name can not be blank")
	@Size(min = 2,max = 70,message = "please provide a valid")
	private String lastName;
	
	@Pattern(regexp = "^[6-9][0-9]{9}",message = "please provide valid mobile number")
	private String mobileNumber;
	
	@Email(regexp = "[a-z0-9._]+@[a-z0-9._]+\\.[a-z]{2,3}",message = "please provide valid email")
	private String email;
	
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	public Customer(@Size(min = 5, max = 19, message = "please provide a suitable password") String password,
			@NotBlank(message = "role can not be blank") String role,
			@NotBlank(message = "first name can not be blank") @Size(min = 2, max = 50, message = "please provide a valid") String firstName,
			@NotBlank(message = "last name can not be blank") @Size(min = 2, max = 70, message = "please provide a valid") String lastName,
			@Pattern(regexp = "^[6-9][0-9]{9}", message = "please provide valid mobile number") String mobileNumber,
			@Email(regexp = "[a-z0-9._]+@[a-z0-9._]+\\.[a-z]{2,3}", message = "please provide valid email") String email,
			Address address) {
		super(password, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.address = address;
	}
	
	
}
