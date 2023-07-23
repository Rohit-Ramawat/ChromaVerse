
package com.masai.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.masai.exception.ProductException;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.repository.CategoryRepositary;
import com.masai.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepositary categoryRepositary;
	
	private final Integer pageSize = 100;

	@Override
	public Product addProduct(Product product) {
		Category existingCategory = categoryRepositary.findByCategoryName(product.getCategory().getCategoryName());

		if (existingCategory == null) {
			return productRepository.save(product);
		} else {
			product.setCategory(existingCategory);
			return productRepository.save(product);
		}
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
	public List<Product> viewAllProducts(Integer page) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return productRepository.findAll(pageable).getContent();
	}

	@Override
	public List<Product> viewProductByCategory(String cname, Integer page) {
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
	public List<Product> searchProducts(String keyword, Integer page) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Product> productsPage = productRepository.searchProducts(keyword, pageable);
		return productsPage.getContent();
	}

	@Override
	public List<Product> getSortedProductsByPrice(String sortOrder, Integer page) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.by("price").descending());
		if ("asc".equalsIgnoreCase(sortOrder)) {
			pageable = PageRequest.of(page, pageSize, Sort.by("price").ascending());
		}
		Page<Product> productsPage = productRepository.findAll(pageable);
		return productsPage.getContent();
	}

	@Override
	public List<Product> filterProducts(List<String> categories, List<String> brands, Double minPrice, Double maxPrice, int page,  String sortOrder) {
	    List<Product> productList = viewAllProducts(page);

	    List<Product> filteredProducts = productList.stream()
	            .filter(product -> categories == null || categories.isEmpty() || (product.getCategory() != null && containsIgnoreCase(categories, product.getCategory().getCategoryName())))
	            .filter(product -> brands == null || brands.isEmpty() || (product.getBrand() != null && containsIgnoreCase(brands, product.getBrand())))
	            .filter(product -> (minPrice == null || product.getPrice() >= minPrice) &&
	                    (maxPrice == null || product.getPrice() <= maxPrice))
	            .collect(Collectors.toList());

	    if ("asc".equalsIgnoreCase(sortOrder)) {
	        Collections.sort(filteredProducts, Comparator.comparingDouble(Product::getPrice));
	    } else if ("desc".equalsIgnoreCase(sortOrder)) {
	        Collections.sort(filteredProducts, Comparator.comparingDouble(Product::getPrice).reversed());
	    }
	    return filteredProducts;
	}

	private boolean containsIgnoreCase(List<String> list, String searchStr) {
	    if (list == null || searchStr == null) {
	        return false;
	    }
	    return list.stream().anyMatch(str -> str.equalsIgnoreCase(searchStr));
	}

	

	@Override
	public List<Category> getCategory() {
		return categoryRepositary.findAll();
	}

	@Override
	public List<String> getBrands() {
        return productRepository.findAllBrands();
    }

}


