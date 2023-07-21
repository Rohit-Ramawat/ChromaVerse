package com.masai.service;

import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.OrderException;
import com.masai.model.Order;
import com.masai.model.Product;
import com.masai.repository.OrderRepository;
import com.masai.repository.ProductRepository;

@Service
public abstract class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;
    
	@Autowired
    private ProductRepository productRepository;

	
	

	public Order createOrder(Order order) {
		
        if (order == null || order.getProductList().isEmpty() || order.getCustomer() == null) {
            throw new IllegalArgumentException("Invalid order data. Order, products, and customer must be provided.");
        }

        if (order.getOrderStatus() == null) {
            order.setOrderStatus("pending");
        }

        order.setOrderDate(LocalDate.now()); 
        
        updateProductQuantities(order); 

        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
    	
        if (order == null || order.getProductList().isEmpty() || order.getCustomer() == null) {
            throw new IllegalArgumentException("Invalid order data. Order, products, and customer must be provided.");
        }

        updateProductQuantities(order); 

        return orderRepository.save(order);
    }

    public void deleteOrder(Integer orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            cancelOrder(order);
            orderRepository.delete(order);
        }
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    
    public void cancelOrder(Order order) {
        if (order != null) {
            for (Product product : order.getProductList()) {
                int orderedQuantity = product.getQuantity();
                product.setQuantity(product.getQuantity() + orderedQuantity);
                productRepository.save(product);
            }

            order.setOrderStatus("canceled");
            orderRepository.save(order);
        }
    }

    public void updateOrderStatus(Order order, String status) {
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        }
    }

    public double calculateOrderTotalPrice(Order order) {
        double totalPrice = 0;
        for (Product product : order.getProductList()) {
            int quantity = product.getQuantity();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void updateProductQuantities(Order order) {
        for (Product product : order.getProductList()) {
            int orderedQuantity = product.getQuantity();
            Product p = productRepository.findById(product.getProductId()).get();
            int availableQuantity = p.getQuantity();

            if (orderedQuantity > availableQuantity) {
                throw new IllegalArgumentException("Requested quantity exceeds available stock for product: " + product.getProductName());
            }

            product.setQuantity(availableQuantity - orderedQuantity);
            productRepository.save(product);
        }
    }
}


