package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
public class ProductController {

	@Autowired
	public ProductService productService;

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products") // Endpoint to retrieve all products
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") Integer page) {
		List<Product> products = productService.viewAllProducts(page);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@PostMapping("/products") // Endpoint to add a new product
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
		Product addedProduct = productService.addProduct(product);
		return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	}

	@SecurityRequirement(name = "demo-openapi")
	@PutMapping("/products") // Endpoint to update an existing product
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Product updatedProduct = productService.updateProduct(product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/{id}") // Endpoint to retrieve a product by its ID
	public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
		Product product = productService.viewProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/category/{cname}") // Endpoint to retrieve products by category name
	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String cname,
			@RequestParam(defaultValue = "0") int page) {
		List<Product> products = productService.viewProductByCategory(cname, page);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@DeleteMapping("/products/{pid}") // Endpoint to remove a product by its ID
	public ResponseEntity<Product> removeProduct(@PathVariable Integer pid) {
		Product removedProduct = productService.removeProduct(pid);
		return new ResponseEntity<>(removedProduct, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/search") // Endpoint to search products by keywords
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword,
			@RequestParam(defaultValue = "0") int page) {
		List<Product> products = productService.searchProducts(keyword, page);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/sorted") // Endpoint to sort products
	public ResponseEntity<List<Product>> getSortedProductsByPrice(@RequestParam(defaultValue = "desc") String sortOrder,
			@RequestParam(defaultValue = "0") int page) {
		List<Product> products = productService.getSortedProductsByPrice(sortOrder, page);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/filter")
	public ResponseEntity<List<Product>> filterProducts(@RequestParam(required = false) List<String> category,
			@RequestParam(required = false) List<String> brand, @RequestParam(required = false) Double minPrice,
			@RequestParam(required = false) Double maxPrice, @RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false)  String sortOrder) {
		return new ResponseEntity<>(productService.filterProducts(category, brand, minPrice, maxPrice, page, sortOrder),
				HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/category")
	public ResponseEntity<List<Category>> getCategory() {
		return new ResponseEntity<List<Category>>(productService.getCategory(), HttpStatus.OK);
	}

	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/products/brand")
	public ResponseEntity<List<String>> getBrand() {
		return new ResponseEntity<List<String>>(productService.getBrands(), HttpStatus.OK);
	}
}