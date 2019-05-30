package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Estoque;
import com.webservicecrud.repository.EstoqueRepository;

@Service
public class EstoqueDAO {

	@Autowired
	EstoqueRepository estoqueRepository;

	/* Para salvar um estoque */

	public Estoque save(Estoque estoque) {
		return estoqueRepository.save(estoque);
	}

	/* Para listar todos os estoques */

	public Iterable<Estoque> findAll() {
		return estoqueRepository.findAll();
	}

	/* Procurar um estoque por id */

	public Estoque findOne(Long id) {
		return estoqueRepository.findOne(id);
	}

	/* Deletar um estoque */

	public void delete(Estoque estoque) {
		estoqueRepository.delete(estoque);
	}

}
