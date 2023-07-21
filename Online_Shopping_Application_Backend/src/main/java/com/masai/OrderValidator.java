package com.masai;

import com.masai.model.Order;

public interface OrderValidator {
    void validateOrder(Order order) throws IllegalArgumentException;
}
