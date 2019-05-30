package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Usuario;
import com.webservicecrud.repository.UsuarioRepository;

@Service
public class UsuarioDAO {

	@Autowired
	UsuarioRepository usuarioRepository;

	/* Para salvar um usuario */

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	/* Para listar todos os usuarios */

	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	/* Procurar um usuario por id */

	public Usuario findOne(Long usuId) {
		return usuarioRepository.findOne(usuId);
	}

	/* Deletar um usuario */

	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	/* Este findOne foi feito para o authController fazer a verificação da senha com o usuario */
	
	public Usuario findByUsuario(String usuario) {
		return usuarioRepository.findByUsuario(usuario);
	}

	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario findByCpf(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}

}
