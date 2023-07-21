package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CartException;
import com.masai.model.Cart;
import com.masai.model.Product;
import com.masai.repository.CartRepository;
import com.masai.repository.ProductRepository;

@Service
public class CartServiceImp implements CartService {
 
	@Autowired	
	private CartRepository cartRepo;
	@Autowired
	private ProductRepository productRepo;
	
	
	@Override
	public Cart addProductToCart(int cartId, int productId, int quantity) {
		Cart c = cartRepo.findById(cartId).orElseThrow(() ->  new CartException("No Cart Found"));
	    Product product = productRepo.findById(productId).orElseThrow(() ->  new CartException("No Product Found"));
	    product.setQuantity(quantity);
	    c.getProducts().add(product);
	    return cartRepo.save(c);
	}
	@Override
	public Cart removeProductFromCart(int cartId, int productId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() ->  new CartException("No Cart Found"));
		Product product = productRepo.findById(productId).orElseThrow(() ->  new CartException("No Product Found"));
		
		cart.getProducts().remove(product);
		
		return cartRepo.save(cart);
	}
	@Override
	public Cart updateProductQuantity(int cartId, int productId, int quantity) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() ->  new CartException("No Cart Found"));
		Product product = productRepo.findById(productId).orElseThrow(() ->  new CartException("No Product Found"));
		List<Product> products = cart.getProducts();
		if(products.contains(product)) {
			product.setQuantity(quantity);
		}
		return cartRepo.save(cart);
	}
	@Override
	public Cart removeAllProducts(int cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() ->  new CartException("No Cart Found"));
		cart.getProducts().clear();
		return cartRepo.save(cart);
	}
	@Override
	public List<Product> viewAllProducts(int cartId){
		Cart cart = cartRepo.findById(cartId).orElseThrow(() ->  new CartException("No Cart Found"));
		
		return cart.getProducts();
	}
}
