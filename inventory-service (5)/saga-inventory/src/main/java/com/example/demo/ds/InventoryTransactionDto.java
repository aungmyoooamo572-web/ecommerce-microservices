package com.example.demo.ds;

import java.util.List;
import java.util.Set;



/*
 * public record OrderCreateDto(
		String orderCode,
		String username,
		String accountNumber,
		String email,
		String address,
		Set<CartItem> cartITems) {

}
 */
public record InventoryTransactionDto(
		String orderCode,
		String username,
		String accountNumber,
		List<CartItem> cartItems) {

}
