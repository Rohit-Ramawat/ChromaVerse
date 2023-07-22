package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Cart;
import com.masai.model.Product;
import com.masai.service.CartService;
import com.masai.service.ProductService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/carts")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@SecurityRequirement(name = "demo-openapi")
	@PostMapping("/{cartId}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable int cartId, @RequestParam int productId, @RequestParam int quantity){
		Cart cart = cartService.addProductToCart(cartId, productId, quantity);
		
		return new ResponseEntity<>(cart, HttpStatus.CREATED);
	}
	
	@SecurityRequirement(name = "demo-openapi")
	@DeleteMapping("/{cartId}")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable int cartId,@RequestParam int productId){
		
		Cart cart = cartService.removeProductFromCart(cartId, productId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	@SecurityRequirement(name = "demo-openapi")
	@PutMapping("/{cartId}")
	public ResponseEntity<Cart> updateProductQuantity(@PathVariable int cartId,@RequestParam int productId,@RequestParam int quantity){
		Cart cart = cartService.updateProductQuantity(cartId, productId, quantity);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	@SecurityRequirement(name = "demo-openapi")
	@DeleteMapping("/removeAll/{cartId}")
	public ResponseEntity<Cart> removeAllProducts(@PathVariable int cartId){
		Cart cart = cartService.removeAllProducts(cartId);
		return new ResponseEntity<>(cart, HttpStatus.OK);
	}
	
	@SecurityRequirement(name = "demo-openapi")
	@GetMapping("/view/{cartId}")
	public ResponseEntity<List<Product>> viewAllProducts(@PathVariable int cartId){
		List<Product> products = cartService.viewAllProducts(cartId);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
