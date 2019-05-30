package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Preco;
import com.webservicecrud.repository.PrecoRepository;

@Service
public class PrecoDAO {

	@Autowired
	PrecoRepository precoRepository;

	/* Para salvar um preco */

	public Preco save(Preco preco) {
		return precoRepository.save(preco);
	}

	/* Para listar todos os precos */

	public Iterable<Preco> findAll() {
		return precoRepository.findAll();
	}

	/* Procurar um preco por id */

	public Preco findOne(Long id) {
		return precoRepository.findOne(id);
	}

	/* Deletar um preco */

	public void delete(Preco preco) {
		precoRepository.delete(preco);
	}

}
