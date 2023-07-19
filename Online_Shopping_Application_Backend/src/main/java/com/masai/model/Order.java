package com.masai.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "OrderTable")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	@PastOrPresent
	private LocalDate orderDate;
	
	@NotBlank(message = "please provide valid orderstatus") 
	private String orderStatus;
	
	@ElementCollection
    @CollectionTable(name = "order_product_mapping", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productList = new HashMap<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
	

	@OneToMany(fetch = FetchType.EAGER)
	private List<Product> products = new ArrayList<>();

	public Order(@PastOrPresent LocalDate orderDate,
			@NotBlank(message = "please provide valid orderstatus") String orderStatus) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
	}

	public Order() {
        // Default constructor
    }

    public Order(Long orderId, LocalDate orderDate, Customer customer, Address address) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customer;
        this.address = address;
    }

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }


    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Product, Integer> productList) {
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        // Check if the product is already in the order
        int existingQuantity = productList.getOrDefault(product, 0);

        // Check if the requested quantity is available in stock
        int availableQuantity = product.getQuantity();
        if (existingQuantity + quantity > availableQuantity) {
            throw new IllegalArgumentException("Requested quantity exceeds available stock.");
        }

        // Update the product's quantity in the order and deduct from the product's total quantity
        productList.put(product, existingQuantity + quantity);
        product.setQuantity(availableQuantity - quantity);
    }

    // Method to remove a certain quantity of a product from the order
    // If the quantity becomes zero, remove the product from the order
    public void removeProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        // Check if the product is in the order
        int existingQuantity = productList.getOrDefault(product, 0);
        if (existingQuantity == 0) {
            throw new IllegalArgumentException("Product not found in the order.");
        }

        // Update the product's quantity in the order and add back to the product's total quantity
        if (existingQuantity - quantity <= 0) {
            productList.remove(product);
        } else {
            productList.put(product, existingQuantity - quantity);
        }
        product.setQuantity(product.getQuantity() + quantity);
    }

	
	
}
