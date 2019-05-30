package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Endereco;
import com.webservicecrud.repository.EnderecoRepository;

@Service
public class EnderecoDAO { 

	@Autowired
	EnderecoRepository enderecoRepository;

	/* Para salvar um endereco */

	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	/* Para listar todos os enderecos */

	public Iterable<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

	/* Procurar um endereco por id */

	public Endereco findOne(Long id) {
		return enderecoRepository.findOne(id);
	}

	/* Deletar um endereco */

	public void delete(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}
	
	public Endereco findByLatitude(String latitude) {
		return enderecoRepository.findByLatitude(latitude);
	}

}
