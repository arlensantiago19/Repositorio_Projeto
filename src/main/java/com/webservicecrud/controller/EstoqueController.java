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

import com.webservicecrud.dao.EstoqueDAO;
import com.webservicecrud.model.Estoque;

@RestController
@RequestMapping("/estoques")
@CrossOrigin(origins = "*")
public class EstoqueController {

	@Autowired
	EstoqueDAO estoqueDAO;

	public EstoqueController(EstoqueDAO estoqueDAO) {
		super();
		this.estoqueDAO = estoqueDAO;
	}

	/* Salvar uma estoque no database */

	@PostMapping
	public ResponseEntity<Estoque> createEstoque(@Valid @RequestBody Estoque estoque) {

		estoque = estoqueDAO.save(estoque);
		return ResponseEntity.ok().body(estoque);
	}

	/* Listar todos os estoques */

	@GetMapping
	public Iterable<Estoque> listarTodosEstoques() {
		return estoqueDAO.findAll();
	}

	/* Buscar um estoque pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Estoque> buscarEstoquePeloId(@PathVariable(value = "id") Long id) {
		Estoque estoque = estoqueDAO.findOne(id);

		if (estoque == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(estoque);
	}

	/* Atualizar um estoque pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Estoque> atualizaEstoque(@PathVariable(value = "id") Long id, @Valid @RequestBody Estoque request) {

		Estoque estoque = estoqueDAO.findOne(id);
		if (estoque == null) {
			return ResponseEntity.notFound().build();
		}

		estoque = estoqueDAO.save(estoque);
		return ResponseEntity.ok().body(estoque);
	}

	/* Deletar um estoque pelo id */

	@DeleteMapping("/{id}")
	public ResponseEntity<Estoque> deletarEstoque(@PathVariable(value = "id") Long id) {

		Estoque estoque = estoqueDAO.findOne(id);
		if (estoque == null) {
			return ResponseEntity.notFound().build();
		}

		estoqueDAO.delete(estoque);

		return ResponseEntity.ok().build();
	}

}
