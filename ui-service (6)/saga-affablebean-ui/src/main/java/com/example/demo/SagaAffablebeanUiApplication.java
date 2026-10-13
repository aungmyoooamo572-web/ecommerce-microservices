package com.example.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.service.registry.ImportHttpServices;

import com.example.demo.client.ProductClient;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
@ImportHttpServices(ProductClient.class)
public class SagaAffablebeanUiApplication {
	
	private final ProductClient productClient;
	
//	@Bean
//	public ApplicationRunner runner() {
//		return r ->{
//			productClient.listProducts("meats")
//			.stream()
//			.forEach(System.out::println);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(SagaAffablebeanUiApplication.class, args);
	}

}
