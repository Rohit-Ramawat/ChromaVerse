package com.masai.service;

import java.util.List;

import com.masai.model.Order;

public interface OrderService {
	List<Order> findOrdersByStatus(String status);

    List<Order> findOrdersByCustomerId(Integer customerId);

//    List<Order> findOrdersByProductId(Integer productId);

    List<Order> findOrdersByTotalPriceGreaterThan(double price);

    List<Order> findOrdersByOrderDateBetween(String startDate, String endDate);
}
