package com.masai.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.category.categoryName LIKE :catName")
	Page<Product> findByCategory(String catName, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:keyword% OR p.description LIKE %:keyword% OR p.brand LIKE %:keyword%")
	Page<Product> searchProducts(String keyword, Pageable pageable);

//	@Query("SELECT p FROM Product p WHERE " + "(:category IS NULL OR p.category.categoryName = :category) "
//			+ "AND (:productName IS NULL OR p.productName = :productName) "
//			+ "AND (:minPrice IS NULL OR p.price >= :minPrice) " + "AND (:maxPrice IS NULL OR p.price <= :maxPrice) "
//			+ "AND (:brand IS NULL OR p.brand = :brand)")
//	List<Product> filterProducts(String category, String productName, Double minPrice, Double maxPrice, String brand);


	

	@Query("SELECT DISTINCT p.brand FROM Product p")
	List<String> findAllBrands();



}
