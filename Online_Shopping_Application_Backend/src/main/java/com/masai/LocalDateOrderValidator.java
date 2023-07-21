package com.masai;
import java.time.LocalDate;
import java.util.Map;

import com.masai.model.Order;
import com.masai.model.Product;

public class LocalDateOrderValidator implements OrderValidator {

    public void validateOrder(Order order) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        // ... (Existing validation rules from the previous example)

        // Validate order date (assuming it should not be null and must be in the past or today)
        LocalDate orderDate = order.getOrderDate();
        if (orderDate == null || orderDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Order date must be provided and cannot be in the future.");
        }

        // Validate product quantities and availability
        for (Map.Entry<Product, Integer> entry : order.getProductList().entrySet()) {
            Product product = entry.getKey();
            int orderedQuantity = entry.getValue();
            int availableQuantity = product.getQuantity();

            if (orderedQuantity <= 0) {
                throw new IllegalArgumentException("Invalid ordered quantity for product: " + product.getProductName());
            }

            if (orderedQuantity > availableQuantity) {
                throw new IllegalArgumentException("Ordered quantity exceeds available stock for product: " + product.getProductName());
            }
        }

        // Add more custom validation rules as needed based on your application's specific requirements
        // For example, checking payment details, shipping options, etc.
    }
}

