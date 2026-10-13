package com.example.demo.ds;

import java.util.Set;

import com.example.demo.entity.CartItem;



public record OrderCreateDto(
		String orderCode,
		String username,
		String accountNumber,
		String email,
		String address,
		Set<CartItem> cartITems) {

}