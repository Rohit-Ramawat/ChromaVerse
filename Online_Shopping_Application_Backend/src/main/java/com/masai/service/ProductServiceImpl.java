package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.masai.exception.ProductException;
import org.springframework.data.domain.Sort;
import com.masai.model.Product;
import com.masai.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

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
	public Product viewProductById(Integer id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("No Product Found"));
		return product;
	}

	@Override
	public List<Product> viewAllProducts(Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
        return  productRepository.findAll(pageable).getContent();
    }

	@Override
	public List<Product> viewProductByCategory(String cname, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> productsPage = productRepository.findByCategory(cname, pageable);
        return productsPage.getContent();
    }

	@Override
	public Product removeProduct(Integer pid) {
		Product product = productRepository.findById(pid).orElseThrow(() -> new ProductException("No Product Found"));
		productRepository.delete(product);
		return product;
	}
	
	@Override
	public List<Product> searchProducts(String keyword, Integer page, Integer pageSize) {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        Page<Product> productsPage = productRepository.searchProducts(keyword, pageable);
//        return productsPage.getContent();
		return null;
    }
	
	@Override
	public List<Product> getSortedProductsByPrice(String sortOrder, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("price").descending());
        if ("asc".equalsIgnoreCase(sortOrder)) {
            pageable = PageRequest.of(page, pageSize, Sort.by("price").ascending());
        }
        Page<Product> productsPage = productRepository.findAll(pageable);
        return productsPage.getContent();
    }
	
	@Override
	public List<Product> filterProducts(String brand, String color, String size, Integer page, Integer pageSize) {
//        Pageable pageable = PageRequest.of(page, pageSize);
//        Page<Product> productsPage = productRepository.findProductsByBrandAndColorAndSize(brand, color, size, pageable);
//        return productsPage.getContent();
        return null;
    }

}