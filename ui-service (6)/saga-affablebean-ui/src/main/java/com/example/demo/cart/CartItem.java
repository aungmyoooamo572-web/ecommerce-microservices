package com.example.demo.cart;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private int quantity;
	private double price;
}
