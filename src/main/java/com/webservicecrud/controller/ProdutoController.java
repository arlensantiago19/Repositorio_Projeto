package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.dao.ProdutoDAO;
import com.webservicecrud.model.Produto;
import com.webservicecrud.model.ProdutoDetalhe;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

	@Autowired
	ProdutoDAO produtoDAO;

	public ProdutoController(ProdutoDAO produtoDAO) {
		super();
		this.produtoDAO = produtoDAO;
	}

	/* Salvar um produto no database */

	@PostMapping
	public ResponseEntity<Produto> createProduto(@Valid @RequestBody Produto produto) {
		Produto usu;
		usu = produtoDAO.findByCodigoBarras(produto.getCodigoBarras());
		if (usu != null) {
			System.out.println("ERRO: Código de barras já cadastrado.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		for (ProdutoDetalhe detalhe : produto.getDetalhes()) {
			detalhe.setProduto(produto);
		}
		produto = produtoDAO.save(produto);

		return ResponseEntity.ok().body(produto);
	}

	/* Listar todos os produtos */

	@GetMapping
	public Iterable<Produto> listarTodosProdutos() {
		return produtoDAO.findAll();
	}

	/* Buscar um produto pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProdutoPeloId(@PathVariable(value = "id") Long usuId) {
		Produto produto = produtoDAO.findOne(usuId);

		if (produto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(produto);
	}

	/* Atualizar um produto pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizaProduto(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Produto usuRequest) {

		Produto produto = produtoDAO.findOne(id);
		if (produto == null) {
			return ResponseEntity.notFound().build();
		}

		Produto usu;
		if (!usuRequest.getCodprod().equals(produto.getCodprod())) {
			usu = produtoDAO.findOne(usuRequest.getCodprod());
			if (usu != null) {
				System.out.println("ERRO: Usuário já existe.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			produto.setCodprod(usuRequest.getCodprod());
		}

		if (!usuRequest.getCodigoBarras().equals(produto.getCodigoBarras())) {
			usu = produtoDAO.findByCodigoBarras(usuRequest.getCodigoBarras());
			if (usu != null) {
				System.out.println("ERRO: Código de barras já em uso.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			produto.setCodigoBarras(usuRequest.getCodigoBarras());
		}

		produto.setDescricao(usuRequest.getDescricao());

		Produto atualizaProduto = produtoDAO.save(produto);
		return ResponseEntity.ok().body(atualizaProduto);
	}

}
