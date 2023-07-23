
package com.masai.service;

import java.util.List;

import com.masai.model.Category;
import com.masai.model.Product;

public interface ProductService {

	public Product addProduct(Product product);

	public Product updateProduct(Product product);

	public Product viewProductById(Integer id);

	public Product removeProduct(Integer pid);

	public List<Product> viewAllProducts(Integer page);

	public List<Product> viewProductByCategory(String cname, Integer page);

	public List<Product> searchProducts(String keyword, Integer page);

	public List<Product> getSortedProductsByPrice(String sortOrder, Integer page);

	public List<Category> getCategory();

	public List<String> getBrands();

	public List<Product> filterProducts(List<String> categories, List<String> brands, Double minPrice, Double maxPrice,  int page,  String sortOrder);
}
