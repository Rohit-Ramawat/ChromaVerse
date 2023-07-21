package com.masai.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Order;
import com.masai.service.OrderService;



@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
    private OrderService orderService;

    @GetMapping("/orders/status")
    public List<Order> findOrdersByStatus(@RequestParam String status) {
        return orderService.findOrdersByStatus(status);
    }

    @GetMapping("/orders/customer")
    public List<Order> findOrdersByCustomerId(@RequestParam Integer customerId) {
        return orderService.findOrdersByCustomerId(customerId);
    }

//    @GetMapping("/orders/product")
//    public List<Order> findOrdersByProductId(@RequestParam Integer productId) {
//        return orderService.findOrdersByProductId(productId);
//    }

    @GetMapping("/orders/price")
    public List<Order> findOrdersByTotalPriceGreaterThan(@RequestParam double price) {
        return orderService.findOrdersByTotalPriceGreaterThan(price);
    }

    @GetMapping("/orders/date")
    public List<Order> findOrdersByOrderDateBetween(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return orderService.findOrdersByOrderDateBetween(startDate, endDate);
    }
}


