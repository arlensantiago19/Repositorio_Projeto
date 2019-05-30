package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Local;
import com.webservicecrud.repository.LocalRepository;

@Service
public class LocalDAO {

	@Autowired
	LocalRepository localRepository;

	/* Para salvar um local */

	public Local save(Local local) {
		return localRepository.save(local);
	}

	/* Para listar todos os locals */

	public Iterable<Local> findAll() {
		return localRepository.findAll();
	}

	/* Procurar um local por id */

	public Local findOne(Long id) {
		return localRepository.findOne(id);
	}

	/* Deletar um local */

	public void delete(Local local) {
		localRepository.delete(local);
	}

}
