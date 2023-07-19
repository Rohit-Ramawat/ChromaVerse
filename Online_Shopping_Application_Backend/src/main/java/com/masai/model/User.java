package com.masai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Size(min = 5,max = 19,message = "please provide a suitable password")
	private String password;
	
	@NotBlank(message = "role can not be blank")
	private String role;

	public User(@Size(min = 5, max = 19, message = "please provide a suitable password") String password,
			@NotBlank(message = "role can not be blank") String role) {
		super();
		this.password = password;
		this.role = role;
	}
	
	
}
