package com.masai.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Page<Product> findByCategory(String cname, Pageable pageable);

	//Page<Product> searchProducts(String keyword, Pageable pageable);

	//Page<Product> findProductsByBrandAndColorAndSize(String brand, String color, String size, Pageable pageable);

}
