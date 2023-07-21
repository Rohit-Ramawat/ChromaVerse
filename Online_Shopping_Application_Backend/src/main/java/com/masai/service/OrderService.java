package com.masai.service;

import com.masai.model.Order;

public interface OrderService {
	
	public Order createOrder(Order order);
	
	public Order getOrderById(Integer orderId);
	
	public void cancelOrder(Integer orderId);
	
	public void updateOrderStatus(Integer orderId, String status);
	
	public double calculateOrderTotalPrice(Integer orderId);
	
	public void updateProductQuantities(Order order);
	
}
