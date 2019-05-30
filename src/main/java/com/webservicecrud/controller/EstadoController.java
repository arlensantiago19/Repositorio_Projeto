package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.webservicecrud.dao.EstadoDAO;
import com.webservicecrud.model.Estado;

@RestController
@RequestMapping("/estados")
@CrossOrigin(origins = "*")
public class EstadoController {

	@Autowired
	EstadoDAO estadoDAO;

	public EstadoController(EstadoDAO estadoDAO) {
		super();
		this.estadoDAO = estadoDAO;
	}
 
	/* Salvar uma estado no database */

	@PostMapping
	public ResponseEntity<Estado> createEstado(@Valid @RequestBody Estado estado) {
		Estado est;

		// Verificar a existência da UF
		String uf = estado.getUf().toUpperCase();
		est = estadoDAO.findByUf(uf);
		if (est != null) {
			System.out.println("ERRO: UF já cadastrada.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		// Setar a UF em uppercase
		estado.setUf(uf);
		
		// Verificar a existência do Estado
		String nome = estado.getEstado().toUpperCase();
		est = estadoDAO.findByEstado(nome);
		if (est != null) {
			System.out.println("ERRO: Estado já cadastrada.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		// Setar o Estado em uppercase
		estado.setEstado(nome);
		
		estado = estadoDAO.save(estado);
		return ResponseEntity.ok().body(estado);
	}

	/* Listar todos os estados */

	@GetMapping
	public Iterable<Estado> listarTodosEstados() {
		return estadoDAO.findAll();
	}

	/* Buscar um estado pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarEstadoPeloId(@PathVariable(value = "id") Long id) {
		Estado estado = estadoDAO.findOne(id);

		if (estado == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(estado);
	}

	/* Atualizar um estado pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizaEstado(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Estado request) {

		Estado estado = estadoDAO.findOne(id);
		if (estado == null) {
			return ResponseEntity.notFound().build();
		}

		Estado outro;
		String uf = request.getUf().toUpperCase();

		if (!uf.equals(estado.getUf())) {
			outro = estadoDAO.findByUf(uf);
			if (outro != null) {
				System.out.println("ERRO: UF já está cadastrada.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			estado.setUf(uf);
		}

		String nome = request.getEstado().toUpperCase();
		
		if (!nome.equals(estado.getEstado())) {
			outro = estadoDAO.findByEstado(nome);
			if (outro != null) {
				System.out.println("ERRO: Estado já está cadastrada.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			estado.setEstado(nome);;
		}

		estado = estadoDAO.save(estado);
		return ResponseEntity.ok().body(estado);
	}

	/* Deletar um estado pelo id */

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> deletarEstado(@PathVariable(value = "id") Long usuId) {

		Estado estado = estadoDAO.findOne(usuId);
		if (estado == null) {
			return ResponseEntity.notFound().build();
		}

		estadoDAO.delete(estado);

		return ResponseEntity.ok().build();
	}

}
