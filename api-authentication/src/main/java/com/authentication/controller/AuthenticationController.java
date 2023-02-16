package com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.domain.User;
import com.authentication.service.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	@PostMapping
    public ResponseEntity login(@RequestBody User user) {

		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(user.getLogin(), 
						user.getPassword());
		
		var authentication = authenticationManager.authenticate(token);
    	return ResponseEntity.ok(tokenService.generateToken(user));
    }
	
}
