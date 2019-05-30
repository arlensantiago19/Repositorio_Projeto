package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservicecrud.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Endereco findByLatitude(String latitude);
	
}
