package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Lote;
import com.webservicecrud.repository.LoteRepository;

@Service
public class LoteDAO {

	@Autowired
	LoteRepository loteRepository;

	/* Para salvar um lote */

	public Lote save(Lote lote) {
		return loteRepository.save(lote);
	}

	/* Para listar todos os lotes */

	public Iterable<Lote> findAll() {
		return loteRepository.findAll();
	}

	/* Procurar um lote por id */

	public Lote findOne(Long id) {
		return loteRepository.findOne(id);
	}

	/* Deletar um lote */

	public void delete(Lote lote) {
		loteRepository.delete(lote);
	}

}
