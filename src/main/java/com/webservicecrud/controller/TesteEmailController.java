package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.services.ValidaEmail;

@RestController
@RequestMapping("/teste")
@CrossOrigin(origins = "*")
public class TesteEmailController {


	@GetMapping
	public ResponseEntity<Boolean> teste(@Valid @RequestBody String id) {
		System.out.println("Email: " + id);
		Boolean resposta = ValidaEmail.isEmail(id);
		System.out.println("Email válido?: " + resposta);

		return ResponseEntity.ok().body(resposta);
	}


}
