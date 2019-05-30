package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservicecrud.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

	Estado findByUf(String uf);
	Estado findByEstado(String estado);
	
}
