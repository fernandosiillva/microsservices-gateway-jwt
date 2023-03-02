package com.authentication.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.authentication.domain.User;

@Service
public class TokenService {

	@Value("api.security.token.secret")
	private String secret;
	
	public String generateToken(User user) {
		
		try {

			Algorithm algorithm = Algorithm.HMAC256(secret);

			return JWT.create()
					.withIssuer("API-authentication")
					.withSubject(user.getLogin())
					.withExpiresAt(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")))
					.sign(algorithm);

		} catch (JWTCreationException exception){
			throw new RuntimeException(exception.getMessage());
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo)
					.withIssuer("API-authentication")
					.build()
					.verify(tokenJWT)
					.getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}
}
