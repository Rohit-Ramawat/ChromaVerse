package com.masai.service;

import java.util.List;

import com.masai.model.Product;

public interface ProductService {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product viewProduct(int id);

	public List<Product> viewAllProducts();

	public List<Product> viewProductByCategory(String cname);

	public Product removeProduct(int pid);
}
