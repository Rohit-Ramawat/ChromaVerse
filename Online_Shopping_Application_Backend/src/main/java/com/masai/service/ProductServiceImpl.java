package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Product;
import com.masai.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product addProduct(Product product) {
		 return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		 return productRepository.save(product);
	}

	@Override
	public Product viewProduct(int id) {
		 return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Product> viewAllProducts() {
		 return productRepository.findAll();
	}

	@Override
	public List<Product> viewProductByCategory(String cname) {
		return productRepository.findByCategory(cname);
	}

	@Override
	public Product removeProduct(int pid) {
		Product product = productRepository.findById(pid).orElse(null);
        if (product != null) {
            productRepository.delete(product);
        }
        return product;
	}

	
}
