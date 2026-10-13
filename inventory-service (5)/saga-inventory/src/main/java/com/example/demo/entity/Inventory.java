package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
@Table(name="product_inventory")
public class Inventory {
	@Id
	private Long id;
	@Column(name="product_id")
	private Long productId;
	private String name;
	private int quantity;

}
