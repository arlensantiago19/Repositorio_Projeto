package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Estado;
import com.webservicecrud.repository.EstadoRepository;

@Service
public class EstadoDAO { 

	@Autowired
	EstadoRepository estadoRepository;

	/* Para salvar um estado */

	public Estado save(Estado estado) {
		return estadoRepository.save(estado);
	}

	/* Para listar todos os estados */

	public Iterable<Estado> findAll() {
		return estadoRepository.findAll();
	}

	/* Procurar um estado por id */

	public Estado findOne(Long id) {
		return estadoRepository.findOne(id);
	}

	/* Deletar um estado */

	public void delete(Estado estado) {
		estadoRepository.delete(estado);
	}

	/* Este findOne foi feito para o authController fazer a verificação da senha com o estado */
	
	public Estado findByUf(String uf) {
		return estadoRepository.findByUf(uf);
	}

	public Estado findByEstado(String estado) {
		return estadoRepository.findByEstado(estado);
	}

}
