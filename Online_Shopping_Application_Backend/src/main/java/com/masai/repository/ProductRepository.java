package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findByCategory(String cname);

}
