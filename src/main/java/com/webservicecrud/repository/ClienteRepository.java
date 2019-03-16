package com.webservicecrud.repository;

import com.webservicecrud.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

}
