package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservicecrud.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Empresa findByInscricao(String inscricao);  // Inscrição Estadual
	Empresa findByEmail(String email);
	Empresa findByCnpj(String cnpj);
	
}
