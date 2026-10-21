package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderCode;
	private String username;
	private String accountNumber;
	private String email;
	private String address;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@CollectionTable(name="order_items")
	@ElementCollection
	private List<CartItem> cartItems=new ArrayList<>();
	public Order(
			String orderCode,
			String username, 
			String accountNumber,
			String email, 
			String address,
			List<CartItem> cartItems) {
		super();
		this.orderCode = orderCode;
		this.username = username;
		this.accountNumber = accountNumber;
		this.email = email;
		this.address = address;
		this.cartItems = cartItems;
	}
	
	
}








