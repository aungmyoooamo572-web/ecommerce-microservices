package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.jwt.TokenProvider;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserDao userDao;
	private final RoleDao roleDao;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	
	public String login(String username,String password) throws JOSEException{
		var auth=new UsernamePasswordAuthenticationToken(username, password);
		var authentication= authenticationManager.authenticate(auth);
		SecurityContextHolder.getContext()
			.setAuthentication(authentication);	
		return tokenProvider.createToken(username);
	}
	
	
	public String register(String username,String password,String email) {
		var user=new User(
				username,
				passwordEncoder.encode(password)
				, email);
		roleDao.findByRoleName("ROLE_USER")
		.ifPresentOrElse( r ->{
			user.getRoles().add(r);
			userDao.save(user);
		}, ()->{
			Role role=new Role();
			role.setRoleName("ROLE_USER");
			user.getRoles().add(roleDao.save(role));
			userDao.save(user);
		});
		return "%s registered successfully!".formatted(username);
		
	}

}







