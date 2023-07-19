package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "OrderTable")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	@PastOrPresent
	private LocalDate orderDate;
	
	@NotBlank(message = "please provide valid orderstatus") 
	private String orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<>();

	public Order(@PastOrPresent LocalDate orderDate,
			@NotBlank(message = "please provide valid orderstatus") String orderStatus) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}
	
	
}
