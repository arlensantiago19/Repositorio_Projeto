package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Cidade;
import com.webservicecrud.repository.CidadeRepository;

@Service
public class CidadeDAO { 

	@Autowired
	CidadeRepository cidadeRepository;

	/* Para salvar um cidade */

	public Cidade save(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	/* Para listar todos os cidades */

	public Iterable<Cidade> findAll() {
		return cidadeRepository.findAll();
	}

	/* Procurar um cidade por id */

	public Cidade findOne(Long id) {
		return cidadeRepository.findOne(id);
	}

	/* Deletar um cidade */

	public void delete(Cidade cidade) {
		cidadeRepository.delete(cidade);
	}

	public Cidade findByCidade(String cidade) {
		return cidadeRepository.findByCidade(cidade);
	}

}
