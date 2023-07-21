package com.masai.service;

import java.util.List;


import com.masai.model.Product;

public interface ProductService {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product viewProductById(Integer id);

	public Product removeProduct(Integer pid);

	public List<Product> viewAllProducts(Integer page, Integer pageSize);

	public List<Product> viewProductByCategory(String cname, Integer page, Integer pageSize);
	
	public List<Product> searchProducts(String keyword, Integer page, Integer pageSize);
	
	public List<Product> getSortedProductsByPrice(String sortOrder, Integer page, Integer pageSize);
	
	public List<Product> filterProducts(String brand, String color, String size, Integer page, Integer pageSize);
}
