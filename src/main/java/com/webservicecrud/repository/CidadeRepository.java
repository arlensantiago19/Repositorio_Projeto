package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservicecrud.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	Cidade findByCidade(String cidade);
	
}
