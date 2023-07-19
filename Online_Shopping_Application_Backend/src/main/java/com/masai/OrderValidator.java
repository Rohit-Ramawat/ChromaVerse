package com.masai;

import java.time.LocalDate;
import java.util.Map;

import com.masai.model.Order;
import com.masai.model.Product;

public class OrderValidator {

    public void validateOrder(Order order) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        // Validate order date (assuming it follows a specific format, e.g., "yyyy-MM-dd")
        LocalDate orderDate = order.getOrderDate();
        if (orderDate == null || orderDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Order date must be provided and cannot be in the future.");
        }
        
        for (Map.Entry<Product, Integer> entry : order.getProductList().entrySet()) {
            Product product = entry.getKey();
            int orderedQuantity = entry.getValue();
            int availableQuantity = product.getQuantity();

            if (orderedQuantity <= 0) {
                throw new IllegalArgumentException("Invalid ordered quantity for product: " + product.getName());
            }

            if (orderedQuantity > availableQuantity) {
                throw new IllegalArgumentException("Ordered quantity exceeds available stock for product: " + product.getName());
            }
        }

        // Validate order status (assuming there are specific allowed status values)
        String orderStatus = order.getOrderStatus();
        if (orderStatus == null || (!orderStatus.equalsIgnoreCase("pending") && !orderStatus.equalsIgnoreCase("completed") && !orderStatus.equalsIgnoreCase("canceled"))) {
            throw new IllegalArgumentException("Invalid order status. Allowed values are: pending, completed, canceled.");
        }

        // Validate product list (assuming it should not be empty)
        if (order.getProductList() == null || order.getProductList().isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one product.");
        }

        // Validate customer and address (assuming they must be provided)
        if (order.getCustomer() == null || order.getAddress() == null) {
            throw new IllegalArgumentException("Customer and address must be provided for the order.");
        }
        
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        // ... (Existing validation rules from the previous example)

        // Validate product quantities and availability
        

        // You can add more validation rules based on your application's specific requirements
        // For example, checking product quantities, product availability, etc.
    }
}

