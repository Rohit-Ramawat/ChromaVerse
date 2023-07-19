package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.model.Cart;
import com.masai.model.Product;
import com.masai.repository.CartRepository;

public class CartService {
 
	@Autowired	
	private CartRepository cartRepo;
	
	public Cart addProductToCart(Cart cart, Product p, int quantity) {
		return null;
	}
	
	public Cart removeProductFromCart(int cartId, int productId) {
		return null;
	}
	public Cart updateProductQuantity(int cartId, int productId, int quantity) {
		return null;
	}
	public Cart removeAllProducts(int cartId) {
		return null;
	}
	public List<Product> viewAllProducts(int cartId){
		return null;
	}
}
