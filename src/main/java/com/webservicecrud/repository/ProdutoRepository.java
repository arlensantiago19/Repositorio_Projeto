package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservicecrud.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByCodigoBarras(String codigoBarras);
	
}
