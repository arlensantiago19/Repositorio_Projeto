package com.webservicecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.webservicecrud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByUsuario(String usuario);
	Usuario findByEmail(String email);
	Usuario findByCpf(String cpf);
	
}
