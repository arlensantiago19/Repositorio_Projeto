package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webservicecrud.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
