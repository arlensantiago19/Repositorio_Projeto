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

import com.webservicecrud.dao.CidadeDAO;
import com.webservicecrud.model.Cidade;

@RestController
@RequestMapping("/cidades")
@CrossOrigin(origins = "*")
public class CidadeController {

	@Autowired
	CidadeDAO cidadeDAO;

	public CidadeController(CidadeDAO cidadeDAO) {
		super();
		this.cidadeDAO = cidadeDAO;
	}
 
	/* Salvar uma cidade no database */

	@PostMapping
	public ResponseEntity<Cidade> createCidade(@Valid @RequestBody Cidade cidade) {

		String nome = cidade.getCidade().toUpperCase();
		cidade.setCidade(nome);
		
		cidade = cidadeDAO.save(cidade);
		return ResponseEntity.ok().body(cidade);
	}

	/* Listar todos os cidades */

	@GetMapping
	public Iterable<Cidade> listarTodosCidades() {
		return cidadeDAO.findAll();
	}

	/* Buscar um cidade pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarCidadePeloId(@PathVariable(value = "id") Long id) {
		Cidade cidade = cidadeDAO.findOne(id);

		if (cidade == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(cidade);
	}

	/* Atualizar um cidade pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Cidade> atualizaCidade(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Cidade request) {

		Cidade cidade = cidadeDAO.findOne(id);
		if (cidade == null) {
			return ResponseEntity.notFound().build();
		}

		cidade.setCidade(request.getCidade().toUpperCase());
		cidade.setCodestado(request.getCodestado());

		cidade = cidadeDAO.save(cidade);
		return ResponseEntity.ok().body(cidade);
	}

	/* Deletar um cidade pelo id */

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Cidade> deletarCidade(@PathVariable(value = "id") Long usuId) {

		Cidade cidade = cidadeDAO.findOne(usuId);
		if (cidade == null) {
			return ResponseEntity.notFound().build();
		}

		cidadeDAO.delete(cidade);

		return ResponseEntity.ok().build();
	}

}
