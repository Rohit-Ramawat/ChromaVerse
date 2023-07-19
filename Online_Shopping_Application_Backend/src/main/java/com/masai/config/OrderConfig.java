package com.masai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.masai.controller.OrderController;
import com.masai.repository.OrderRepository;
import com.masai.service.OrderService;

@Configuration
public class OrderConfig {

    @Bean
    public OrderService orderService(OrderRepository orderRepository, ProductRepository productRepository) {
        return new OrderService(orderRepository, productRepository);
    }

    @Bean
    public OrderController orderController(OrderService orderService) {
        return new OrderController(orderService);
    }

    @Bean
    public OrderValidator orderValidator() {
        return new OrderValidator();
    }

    @Bean
    public OrderMapper orderMapper() {
        return new OrderMapper();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

}

