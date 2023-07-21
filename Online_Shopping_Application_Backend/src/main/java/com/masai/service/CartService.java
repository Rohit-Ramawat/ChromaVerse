package com.masai.service;

import java.util.List;

import com.masai.model.Cart;
import com.masai.model.Product;


public interface CartService {
	
	public Cart addProductToCart(int cartId, int productIds, int quantity);
	public Cart removeProductFromCart(int cartId, int productId);
	public Cart updateProductQuantity(int cartId, int productId, int quantity);
	public Cart removeAllProducts(int cartId);
	public List<Product> viewAllProducts(int cartId);
	

}
