package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.dao.PrecoDAO;
import com.webservicecrud.model.Preco;

@RestController
@RequestMapping("/precos")
@CrossOrigin(origins = "*")
public class PrecoController {

	@Autowired
	PrecoDAO precoDAO;

	public PrecoController(PrecoDAO precoDAO) {
		super();
		this.precoDAO = precoDAO;
	}

	/* Salvar uma preco no database */

	@PostMapping
	public ResponseEntity<Preco> createPreco(@Valid @RequestBody Preco preco) {

		preco = precoDAO.save(preco);
		return ResponseEntity.ok().body(preco);
	}

	/* Listar todos os precos */

	@GetMapping
	public Iterable<Preco> listarTodosPrecos() {
		return precoDAO.findAll();
	}

	/* Buscar um preco pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Preco> buscarPrecoPeloId(@PathVariable(value = "id") Long id) {
		Preco preco = precoDAO.findOne(id);

		if (preco == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(preco);
	}

	/* Atualizar um preco pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Preco> atualizaPreco(@PathVariable(value = "id") Long id, @Valid @RequestBody Preco request) {

		Preco preco = precoDAO.findOne(id);
		if (preco == null) {
			return ResponseEntity.notFound().build();
		}

		preco = precoDAO.save(preco);
		return ResponseEntity.ok().body(preco);
	}

	/* Deletar um preco pelo id */

	@DeleteMapping("/{id}")
	public ResponseEntity<Preco> deletarPreco(@PathVariable(value = "id") Long usuId) {

		Preco preco = precoDAO.findOne(usuId);
		if (preco == null) {
			return ResponseEntity.notFound().build();
		}

		precoDAO.delete(preco);

		return ResponseEntity.ok().build();
	}

}
