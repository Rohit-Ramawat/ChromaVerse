package com.masai.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.masai.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	@Query("SELECT o FROM Order o WHERE o.status = ?1")
    List<Order> findByStatus(String status);

    @Query("SELECT o FROM Order o WHERE o.customer.id = ?1")
    List<Order> findByCustomerId(Integer customerId);

//    @Query("SELECT o FROM Order o WHERE o.product.productId = ?1")
//    List<Order> findByProductId(Integer productId);

    @Query("SELECT o FROM Order o WHERE o.totalPrice > ?1")
    List<Order> findByTotalPriceGreaterThan(double price);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN ?1 AND ?2")
    List<Order> findByOrderDateBetween(String startDate, String endDate);
}

