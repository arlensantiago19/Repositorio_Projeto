package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Produto;
import com.webservicecrud.repository.ProdutoRepository;

@Service
public class ProdutoDAO {

	@Autowired
	ProdutoRepository produtoRepository;

	/* Para salvar um produto */

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	/* Para listar todos os produtos */

	public Iterable<Produto> findAll() {
		return produtoRepository.findAll();
	}

	/* Procurar um produto por id */

	public Produto findOne(Long id) {
		return produtoRepository.findOne(id);
	}

	/* Deletar um produto */

	public void delete(Produto produto) {
		produtoRepository.delete(produto);
	}

	/* Este findOne foi feito para o authController fazer a verificação da senha com o produto */
	
	public Produto findByCodigoBarras(String codigoBarras) {
		return produtoRepository.findByCodigoBarras(codigoBarras);
	}

}
