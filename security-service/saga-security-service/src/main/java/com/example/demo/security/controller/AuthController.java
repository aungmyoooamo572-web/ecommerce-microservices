package com.example.demo.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.AuthService;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	record RegisterDto(String username,String password,String email) {}
	record LoginDto(String username,String password) {}
	
	@PostMapping("/login")
	public ResponseEntity<String> register(@RequestBody LoginDto dto)
			throws JOSEException{
		String returnString= authService.login(dto.username,dto.password);
		return ResponseEntity.ok().body(returnString);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto dto){
		String returnString=authService.register(
				dto.username,
				dto.password,
				dto.email);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(returnString);
	}
	
	

}
