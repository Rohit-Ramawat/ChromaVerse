package com.masai.controller;

import org.springframework.web.bind.annotation.*;

import com.masai.model.Order;
import com.masai.model.Product;
import com.masai.service.OrderService;
import com.masai.service.OrderServiceImpl;
import com.masai.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private ProductService productService;
	
	

    // Endpoint to create an order
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get order details by order ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to cancel an order by order ID
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Integer orderId) {
    	
        orderService.cancelOrder(orderId);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to update the status of an order by order ID
    @PutMapping("/{orderId}/status")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Integer orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to calculate the total price of an order by order ID
    @GetMapping("/{orderId}/totalprice")
    public ResponseEntity<Double> calculateOrderTotalPrice(@PathVariable Integer orderId) {
        double totalPrice = orderService.calculateOrderTotalPrice(orderId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

 // Endpoint to add a product to an order
    @PostMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Void> addProductToOrder(@PathVariable Integer orderId, @PathVariable Integer productId, @RequestParam int quantity) {
        
    	Order order = orderService.getOrderById(orderId);
        
        Product product = productService.viewProductById(productId);

        if (order == null || product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            //order.addProduct(product, quantity);
            orderService.createOrder(order); // Save the updated order with added product
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to remove a product from an order
    @DeleteMapping("/{orderId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromOrder(@PathVariable Integer orderId, @PathVariable Integer productId, @RequestParam int quantity) {
        Order order = orderService.getOrderById(orderId);
        Product product = productService.viewProductById(productId);

        if (order == null || product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            //order.removeProduct(product, quantity);
            orderService.createOrder(order); // Save the updated order with removed product
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}


