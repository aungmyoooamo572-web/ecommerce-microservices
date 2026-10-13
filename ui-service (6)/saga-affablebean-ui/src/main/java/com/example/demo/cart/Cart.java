package com.example.demo.cart;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@RedisHash("Cart")
@Data
public class Cart {
	@Id
	private String id;
	private Set<CartItem> items=new HashSet<>();
	
	public void addItem(CartItem item) {
		items.add(item);
	}
	
	public void removeItem(long productId) {
		items.removeIf(item -> item.getId() == productId);
	}
	
	
}
