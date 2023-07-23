package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.masai.model.Category;


public interface CategoryRepositary extends JpaRepository<Category, Integer>{

	Category findByCategoryName(String categoryName);
}