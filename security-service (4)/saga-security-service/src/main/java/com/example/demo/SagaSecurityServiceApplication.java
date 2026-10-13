package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SagaSecurityServiceApplication {
	
	/*
	 * 
	 * User   Client    AuthServer    ResourceSrever
	 */

	public static void main(String[] args) {
		SpringApplication.run(SagaSecurityServiceApplication.class, args);
	}

}
