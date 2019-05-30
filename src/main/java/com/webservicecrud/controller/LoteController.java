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
import com.webservicecrud.dao.LoteDAO;
import com.webservicecrud.model.Estoque;
import com.webservicecrud.model.Lote;

@RestController
@RequestMapping("/lotes")
@CrossOrigin(origins = "*")
public class LoteController {

	@Autowired
	LoteDAO loteDAO;

	public LoteController(LoteDAO loteDAO) {
		super();
		this.loteDAO = loteDAO;
	}

	/* Salvar uma lote no database */

	@PostMapping
	public ResponseEntity<Lote> createLote(@Valid @RequestBody Lote lote) {

		Long codestoque = lote.getCodestoque();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		Estoque estoque = estoqueDAO.findOne(codestoque);
		if (estoque == null) {
			return ResponseEntity.notFound().build();
		}
		Long quantidade = estoque.getQuantidade() + lote.getQuantidade();
		System.out.println("Quantidade anterior: " + estoque.getQuantidade());
		System.out.println("Quantidade lote    : " + lote.getQuantidade());
		System.out.println("Quantidade final   : " + quantidade);
		estoque.setQuantidade(quantidade);
		estoqueDAO.save(estoque);
		lote = loteDAO.save(lote);
		return ResponseEntity.ok().body(lote);
	}

	/* Listar todos os lotes */

	@GetMapping
	public Iterable<Lote> listarTodosLotes() {
		return loteDAO.findAll();
	}

	/* Buscar um lote pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Lote> buscarLotePeloId(@PathVariable(value = "id") Long id) {
		Lote lote = loteDAO.findOne(id);

		if (lote == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(lote);
	}

	/* Atualizar um lote pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Lote> atualizaLote(@PathVariable(value = "id") Long id, @Valid @RequestBody Lote request) {

		Lote lote = loteDAO.findOne(id);
		if (lote == null) {
			return ResponseEntity.notFound().build();
		}

		lote = loteDAO.save(lote);
		return ResponseEntity.ok().body(lote);
	}

	/* Deletar um lote pelo id */

	@DeleteMapping("/{id}")
	public ResponseEntity<Lote> deletarLote(@PathVariable(value = "id") Long id) {

		Lote lote = loteDAO.findOne(id);
		if (lote == null) {
			return ResponseEntity.notFound().build();
		}

		loteDAO.delete(lote);

		return ResponseEntity.ok().build();
	}

}
