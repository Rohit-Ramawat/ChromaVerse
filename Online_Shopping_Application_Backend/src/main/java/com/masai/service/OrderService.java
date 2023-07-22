package com.masai.service;

import java.util.List;
import com.masai.model.Order;

public interface OrderService {
	
	public Order placeOrder(Integer userId,Order order);
	
	public Order cancelOrder(Integer orderId);
	
	public List<Order> findOrdersByStatus(String status);

	public List<Order> findOrdersByCustomerId(Integer customerId);

//    List<Order> findOrdersByProductId(Integer productId);

	public List<Order> findOrdersByTotalPriceGreaterThan(double price);

	public List<Order> findOrdersByOrderDateBetween(String startDate, String endDate);

	
}
