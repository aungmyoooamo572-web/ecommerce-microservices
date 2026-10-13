package com.example.demo.cart;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import com.example.demo.ds.Product;

import lombok.RequiredArgsConstructor;

@Service

public class CartService {
	private final CartRepository cartRepository;
	
	
	
	public CartService(CartRepository cartRepository) {
		super();
		this.cartRepository = cartRepository;
	
	}

	public Cart getMyCart() {
		String sessionId=getBrowserSessionId();
		return cartRepository.findById(sessionId)
				.orElseGet(() ->{
					Cart newCart=new Cart();
					newCart.setId(sessionId);
					return newCart;
				});
	}
	
	public void addToCart(Product product) {
		String sessionId=getBrowserSessionId();
		Cart cart=cartRepository.findById(sessionId)
				.orElseGet(()->{
					Cart newCart=new Cart();
					newCart.setId(sessionId);
					return newCart;
				});
		cart.addItem(toCartItem(product));
		cartRepository.save(cart);
	}
	
	
	private CartItem toCartItem(Product product) {
		return new CartItem(
				product.id(),
				product.name(),
				1,
				product.price());
	}
	
	private String getBrowserSessionId() {
		return RequestContextHolder
				.currentRequestAttributes()
				.getSessionId();
	}
	
	
}
