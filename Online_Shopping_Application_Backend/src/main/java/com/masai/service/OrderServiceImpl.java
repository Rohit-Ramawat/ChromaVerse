package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Order;
import com.masai.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    @Override
    public List<Order> findOrdersByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

//    @Override
//    public List<Order> findOrdersByProductId(Integer productId) {
//        return orderRepository.findByProductId(productId);
//    }

    @Override
    public List<Order> findOrdersByTotalPriceGreaterThan(double price) {
        return orderRepository.findByTotalPriceGreaterThan(price);
    }

    @Override
    public List<Order> findOrdersByOrderDateBetween(String startDate, String endDate) {
        return orderRepository.findByOrderDateBetween(startDate, endDate);
    }
}


