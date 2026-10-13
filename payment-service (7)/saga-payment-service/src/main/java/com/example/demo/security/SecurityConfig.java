package com.example.demo.security;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;


import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final AuthenticationEntryPoint authenticationEntryPoint;
	public static final String SECRET_KEY
	="my-256-bit-extremely-secret-key-for-jwt-signing-key";
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)
		throws Exception{
//		http.httpBasic(Customizer.withDefaults());
		http.exceptionHandling( c -> c
				.authenticationEntryPoint(authenticationEntryPoint));
		http.csrf(c -> c.disable());
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
		http.oauth2ResourceServer(oauth2 ->{
			oauth2.jwt(jwt -> jwt.decoder(jwtDecoder()));
		});
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		SecretKeySpec secretKey=
				new SecretKeySpec(SECRET_KEY
						.getBytes(),"HmacSHA256");
		return NimbusJwtDecoder.withSecretKey(secretKey).build();
	}
	
	
	
	
}
