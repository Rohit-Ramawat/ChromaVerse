package com.masai.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.OrderValidator;
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
	
	@Autowired
	private OrderValidator orderValidator;

	public Order createOrder(Order order) {
        orderValidator.validateOrder(order);
        // Additional business logic can be implemented here if needed
        updateProductQuantities(order); // Update product quantities before saving the order
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        orderValidator.validateOrder(order);
        // Additional business logic for updating an order can be implemented here if needed
        updateProductQuantities(order); // Update product quantities after order update
        return orderRepository.save(order);
    }

    public void deleteOrder(int orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            cancelOrder(order);
            orderRepository.delete(order);
        }
    }

    public Order getOrderById(int orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // Method to cancel an order
    public void cancelOrder(Order order) {
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
    public void updateOrderStatus(Order order, String status) {
        if (order != null) {
            order.setOrderStatus(status);
            orderRepository.save(order);
        }
    }

    // Method to calculate the total price of an order
    public double calculateOrderTotalPrice(Order order) {
    	double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry : order.getProductList().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += product.getPrice() * quantity;
        }
        return totalPrice;
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


