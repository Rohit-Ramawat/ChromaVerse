package com.masai.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.masai.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT o FROM Order o WHERE o.orderStatus = :status")
    List<Order> findOrdersByStatus(String status);

    // Custom query to fetch orders by customer ID
    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
    List<Order> findOrdersByCustomerId(Long customerId);

    // Custom query to fetch orders by product ID
    @Query("SELECT o FROM Order o JOIN o.productList p WHERE p.key.productId = :productId")
    List<Order> findOrdersByProductId(Long productId);

    // Custom query to fetch orders within a date range
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersByDateRange(String startDate, String endDate);

    // Custom query to fetch orders with a total price greater than a specified amount
    @Query("SELECT o FROM Order o WHERE o.totalPrice > :minPrice")
    List<Order> findOrdersByTotalPriceGreaterThan(double minPrice);
}

