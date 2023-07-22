package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.OrderException;
import com.masai.model.Customer;
import com.masai.model.Order;
import com.masai.model.Product;
import com.masai.repository.CustomerRepository;
import com.masai.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
    private CustomerRepository customerRepository;
	
	@Override
	public Order placeOrder(Integer userId,Order order) {
		
		Customer savedCustomer = customerRepository.findById(userId)
    			.orElseThrow(() -> new CustomerException("Invalid Customer id"));
		
		double totalPrice = 0.0;
		
        for (Product product : order.getProduct()) {
        	
            double productTotalPrice = product.getPrice() * product.getQuantity();
            
            totalPrice += productTotalPrice;
            
        }
        
        order.setTotalPrice(totalPrice);
        
        order.setCustomer(savedCustomer);
        
        List<Order> orderList = savedCustomer.getOrders();
        
        orderList.add(order);
        
        savedCustomer.setOrders(orderList);
        
        return orderRepository.save(order);
	}
	
	@Override
	public Order cancelOrder(Integer orderId) {
		
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("invalid order id"));
		
        if (order.getStatus().equals("pending")) {
        	
            order.setStatus("cancelled");
            
            orderRepository.save(order);
            
        }
        
        return order;
        
	}


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


