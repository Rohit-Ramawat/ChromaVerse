package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Order;
import com.masai.service.OrderService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;



@RestController
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	
	@SecurityRequirement(name = "demo-openapi")
	@PostMapping("/orders/{userId}")
	public ResponseEntity<Order> placeOrderhandler( @PathVariable("userId") Integer userId, 
											@Valid @RequestBody Order order) {
		
		Order placedOrder = orderService.placeOrder(userId,order);
		
		return new ResponseEntity<Order>(placedOrder,HttpStatus.CREATED);
	}
	
	@SecurityRequirement(name = "demo-openapi")
    @DeleteMapping("/orders/cancel")
    public ResponseEntity<Order> cancelOrder(@RequestParam Integer orderId) {
		
		Order canceledOrder = orderService.cancelOrder(orderId);
		
		return new ResponseEntity<Order>(canceledOrder,HttpStatus.OK);
    }
	

	@SecurityRequirement(name = "demo-openapi")
    @GetMapping("/orders/{status}")
    public List<Order> findOrdersByStatus(@PathVariable("status") String status) {
        return orderService.findOrdersByStatus(status);
    }

    @SecurityRequirement(name = "demo-openapi")
    @GetMapping("/orders/{customerId}")
    public List<Order> findOrdersByCustomerId(@PathVariable("customerId") Integer customerId) {
        return orderService.findOrdersByCustomerId(customerId);
    }

//    @GetMapping("/orders/product")
//    public List<Order> findOrdersByProductId(@RequestParam Integer productId) {
//        return orderService.findOrdersByProductId(productId);
//    }

    @SecurityRequirement(name = "demo-openapi")
    @GetMapping("/orders/{price}")
    public List<Order> findOrdersByTotalPriceGreaterThan(@PathVariable("price") double price) {
        return orderService.findOrdersByTotalPriceGreaterThan(price);
    }

    @SecurityRequirement(name = "demo-openapi")
    @GetMapping("/orders/date")
    public List<Order> findOrdersByOrderDateBetween(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return orderService.findOrdersByOrderDateBetween(startDate, endDate);
    }
}


