package com.masai.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.OrderException;
import com.masai.model.Order;
import com.masai.model.Product;
import com.masai.repository.OrderRepository;
import com.masai.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;
    
	@Autowired
    private ProductRepository productRepository;

    public Order createOrder(Order order) {
        // Additional business logic can be implemented here if needed
        updateProductQuantities(order); // Update product quantities before saving the order
        return orderRepository.save(order);
    }

    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->new OrderException("Invalid orderId"));
    }

    public void cancelOrder(Integer orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            // If the order is canceled, add back the ordered product quantities to the total product quantities
            for (Map.Entry<Product, Integer> entry : order.getProductList().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                product.setQuantity(product.getQuantity() + quantity);
            }
            order.setOrderStatus("canceled");
            orderRepository.save(order);
        }
    }

    // Method to update the status of an order
    public void updateOrderStatus(Integer orderId, String status) {
        Order order = getOrderById(orderId);
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        }
    }

    // Method to calculate the total price of an order
    public double calculateOrderTotalPrice(Integer orderId) {
    	
        Order order = getOrderById(orderId);
        
        if (order != null) {
            return OrderServiceImpl.getTotalPrice();
        }
        
        return 0;
    }

    public static double getTotalPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Helper method to update product quantities based on the ordered products
    public void updateProductQuantities(Order order) {
    	
        for (Map.Entry<Product, Integer> entry : order.getProductList().entrySet()) {
            Product product = entry.getKey();
            int orderedQuantity = entry.getValue();
            int availableQuantity = product.getQuantity();

            if (orderedQuantity > availableQuantity) {
                throw new IllegalArgumentException("Requested quantity exceeds available stock for product: " + product.getProductName());
            }

            product.setQuantity(availableQuantity - orderedQuantity);
            productRepository.save(product);
        }
    }


}


