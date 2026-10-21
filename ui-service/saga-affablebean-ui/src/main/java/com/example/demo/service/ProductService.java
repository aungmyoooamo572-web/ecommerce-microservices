package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.client.ProductClient;
import com.example.demo.ds.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	private final ProductClient productClient;
	
	public List<Product> listProduct(String name){
		return productClient.listProducts(name);
	}
	
	public Product findProductById(int id) {
		return productClient.findProductById(id);
	}
}




