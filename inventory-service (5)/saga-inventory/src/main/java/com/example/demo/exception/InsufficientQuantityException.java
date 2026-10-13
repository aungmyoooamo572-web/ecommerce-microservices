package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientQuantityException extends ResponseStatusException{
	public InsufficientQuantityException() {
		super(HttpStatus.BAD_REQUEST, "Insufficient Quanitity!");
	}
}
