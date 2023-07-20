package com.masai.model;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "OrderTable")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
	
	@JsonFormat(pattern = "dd/MM/YYYY")
    private LocalDate orderDate;
    
    private String orderStatus = "pending";
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Map<Product, Integer> productList = new HashMap<>();
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    

}

    private Long orderId;
    private LocalDate orderDate;
    private String orderStatus = "pending";
    private Map<Product, Integer> productList = new HashMap<>();
    private Customer customer;
    private Address address;

    // Constructors
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

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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
        productList.put(product, productList.getOrDefault(product, 0) + quantity);
    }

    public void removeProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        int currentQuantity = productList.getOrDefault(product, 0);
        int newQuantity = currentQuantity - quantity;
        if (newQuantity <= 0) {
            productList.remove(product);
        } else {
            productList.put(product, newQuantity);
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }
}


