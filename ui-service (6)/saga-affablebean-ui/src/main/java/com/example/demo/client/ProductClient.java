package com.example.demo.client;

import java.util.List;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import com.example.demo.ds.Product;

@HttpExchange("http://localhost:8080/api/backend/products")
public interface ProductClient {
	
	@GetExchange("/product/{id}")
	Product findProductById(@PathVariable int id);
	
	@GetExchange("/{name}")
	List<Product> listProducts(@PathVariable("name")String categoryName);
}
