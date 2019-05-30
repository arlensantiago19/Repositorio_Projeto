package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.dao.EnderecoDAO;
import com.webservicecrud.model.Endereco;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*")
public class EnderecoController {

	@Autowired
	EnderecoDAO enderecoDAO;

	public EnderecoController(EnderecoDAO enderecoDAO) {
		super();
		this.enderecoDAO = enderecoDAO;
	}

	/* Salvar uma endereco no database */

	@PostMapping
	public ResponseEntity<Endereco> createEndereco(@Valid @RequestBody Endereco endereco) {
		endereco = enderecoDAO.save(endereco);
		return ResponseEntity.ok().body(endereco);
	}

	/* Listar todos os enderecos */

	@GetMapping
	public Iterable<Endereco> listarTodosEnderecos() {
		return enderecoDAO.findAll();
	}

	/* Buscar um endereco pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPeloId(@PathVariable(value = "id") Long id) {
		Endereco endereco = enderecoDAO.findOne(id);

		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(endereco);
	}

	/* Atualizar um endereco pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Endereco> atualizaEndereco(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Endereco request) {

		Endereco endereco = enderecoDAO.findOne(id);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}

		endereco = enderecoDAO.save(endereco);
		return ResponseEntity.ok().body(endereco);
	}

	/* Deletar um endereco pelo id */

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Endereco> deletarEndereco(@PathVariable(value = "id") Long usuId) {

		Endereco endereco = enderecoDAO.findOne(usuId);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}

		enderecoDAO.delete(endereco);

		return ResponseEntity.ok().build();
	}

}
