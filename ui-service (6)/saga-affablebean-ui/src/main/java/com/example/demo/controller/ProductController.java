package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.cart.CartService;
import com.example.demo.ds.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/ui/products")
public class ProductController {
	
	private final CartService cartService;
	private final ProductService productService;
	
	// http://localhost:8080/api/ui/products/meats
	@GetMapping
	public String listProducts(@RequestParam("name")String name,Model model) {
		model.addAttribute("products",productService.listProduct(name));
		this.currrentCategoryName=name;
		return "product/products";
	}
	
	private String currrentCategoryName;
	// /api/ui/products/add-to-cart?id=
	@GetMapping("/add-to-cart")
	public String addToCart(@RequestParam("id")int id) {
		Product product=productService.findProductById(id);
		cartService.addToCart(product);
		return "redirect:/api/ui/products?name="+currrentCategoryName;
	}
	
	
	@GetMapping("/home")
	public String landingPage() {
		return "home";
	}
	@ModelAttribute("cartSize")
	public int cartSize() {
		return cartService.getMyCart()
				.getItems().size();
	}

}




