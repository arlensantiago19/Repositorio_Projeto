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

import com.webservicecrud.dao.LocalDAO;
import com.webservicecrud.model.Local;

@RestController
@RequestMapping("/locais")
@CrossOrigin(origins = "*")
public class LocalController {

	@Autowired
	LocalDAO localDAO;

	public LocalController(LocalDAO localDAO) {
		super();
		this.localDAO = localDAO;
	}

	/* Salvar uma local no database */

	@PostMapping
	public ResponseEntity<Local> createLocal(@Valid @RequestBody Local local) {

		local = localDAO.save(local);
		return ResponseEntity.ok().body(local);
	}

	/* Listar todos os locals */

	@GetMapping
	public Iterable<Local> listarTodosLocals() {
		return localDAO.findAll();
	}

	/* Buscar um local pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Local> buscarLocalPeloId(@PathVariable(value = "id") Long id) {
		Local local = localDAO.findOne(id);

		if (local == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(local);
	}

	/* Atualizar um local pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Local> atualizaLocal(@PathVariable(value = "id") Long id, @Valid @RequestBody Local request) {

		Local local = localDAO.findOne(id);
		if (local == null) {
			return ResponseEntity.notFound().build();
		}

		local = localDAO.save(local);
		return ResponseEntity.ok().body(local);
	}

	/* Deletar um local pelo id */

	@DeleteMapping("/{id}")
	public ResponseEntity<Local> deletarLocal(@PathVariable(value = "id") Long usuId) {

		Local local = localDAO.findOne(usuId);
		if (local == null) {
			return ResponseEntity.notFound().build();
		}

		localDAO.delete(local);

		return ResponseEntity.ok().build();
	}

}
