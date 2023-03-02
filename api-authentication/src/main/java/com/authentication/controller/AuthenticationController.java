package com.authentication.controller;

import com.authentication.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

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
		
		authenticationManager.authenticate(token);
		Token tokenDTO = Token.builder().token(tokenService.generateToken(user)).build();
		return ResponseEntity.ok(tokenDTO);
    }

	@GetMapping("/produto")
	public ResponseEntity<?> busca(){

		return ResponseEntity.ok("Notebook");
	};
}