package com.produto.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

	@GetMapping
	public ResponseEntity<?> busca(){

		return ResponseEntity.ok("Notebook");
	};
}
