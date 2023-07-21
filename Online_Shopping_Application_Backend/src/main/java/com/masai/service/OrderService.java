package com.masai.service;

import com.masai.model.Order;

public interface OrderService {
	
	public Order createOrder(Order order);
	
	public Order getOrderById(Integer orderId);
	
	public void cancelOrder(int orderId);
	
	public void updateOrderStatus(Order order, String status);
	
	public double calculateOrderTotalPrice(Order order);
	
	public void updateProductQuantities(Order order);

	public Order updateOrder(Order order);

	public void cancelOrder(Order order);
	
}
