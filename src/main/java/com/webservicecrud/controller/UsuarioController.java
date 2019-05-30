package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.dao.UsuarioDAO;
import com.webservicecrud.model.Usuario;
import com.webservicecrud.services.EncryptionService;
import com.webservicecrud.services.ValidaCPF;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioDAO usuarioDAO;

	public UsuarioController(UsuarioDAO usuarioDAO) {
		super();
		this.usuarioDAO = usuarioDAO;
	}

	/* Salvar um usuario no database */

	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody Usuario usuario) {
		Usuario usu;
		usu = usuarioDAO.findByUsuario(usuario.getUsuario());
		if (usu != null) {
			System.out.println("ERRO: Usuário já existe.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		usu = usuarioDAO.findByEmail(usuario.getEmail());
		if (usu != null) {
			System.out.println("ERRO: Email já em uso.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		// Validar o CPF da entrada
		String cpf = usuario.getCpf();
		if (ValidaCPF.isCPF(cpf)) {
			System.out.println("INFO: CPF válido! " + cpf);
		} else {
			System.out.println("ERRO: CPF inválido! " + cpf);
		}
		cpf = ValidaCPF.imprimeCPFSemMascara(cpf);
		usuario.setCpf(cpf);
		usu = usuarioDAO.findByCpf(cpf);
		if (usu != null) {
			System.out.println("ERRO: Cpf já registrado.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		usuario.setSenha(EncryptionService.criptografar(usuario.getSenha()));
		usuario = usuarioDAO.save(usuario);
		return ResponseEntity.ok().body(usuario);
	}

	/* Listar todos os usuarios */

	@GetMapping
	public Iterable<Usuario> listarTodosUsuarios() {
		return usuarioDAO.findAll();
	}

	/* Buscar um usuario pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuarioPeloId(@PathVariable(value = "id") Long usuId) {
		Usuario usuario = usuarioDAO.findOne(usuId);

		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(usuario);
	}

	/* Atualizar um usuario pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizaUsuario(@PathVariable(value = "id") Long usuId,
			@Valid @RequestBody Usuario usuRequest) {

		Usuario usuario = usuarioDAO.findOne(usuId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		Usuario usu;
		if (!usuRequest.getUsuario().equals(usuario.getUsuario())) {
			usu = usuarioDAO.findByUsuario(usuRequest.getUsuario());
			if (usu != null) {
				System.out.println("ERRO: Usuário já existe.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			usuario.setUsuario(usuRequest.getUsuario());
		}

		System.out.println("INFO: Email " + usuario.getEmail() + " - " + usuRequest.getEmail());
		if (!usuRequest.getEmail().equals(usuario.getEmail())) {
			usu = usuarioDAO.findByEmail(usuRequest.getEmail());
			if (usu != null) {
				System.out.println("ERRO: Email já em uso.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			usuario.setEmail(usuRequest.getEmail());
		}

		// Validar o CPF da entrada
		String cpf = usuRequest.getCpf();
		if (ValidaCPF.isCPF(cpf)) {
			System.out.println("INFO: CPF válido! " + cpf);
		} else {
			System.out.println("ERRO: CPF inválido! " + cpf);
			return ResponseEntity.badRequest().build();
		}
		String cpfRequest = ValidaCPF.imprimeCPFSemMascara(cpf);
		String cpfBase = usuario.getCpf();
		if (!cpfRequest.equals(cpfBase)) {
			usu = usuarioDAO.findByCpf(cpfRequest);
			if (usu != null) {
				System.out.println("ERRO: Cpf já registrado.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			usuario.setCpf(cpfRequest);
		}
		
		usuario.setNome(usuRequest.getNome());
		usuario.setSenha(EncryptionService.criptografar(usuRequest.getSenha()));

		Usuario atualizaUsuario = usuarioDAO.save(usuario);
		return ResponseEntity.ok().body(atualizaUsuario);
	}

	/* Deletar um usuario pelo id */

//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable(value = "id") Long usuId) {

		Usuario usuario = usuarioDAO.findOne(usuId);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}

		usuarioDAO.delete(usuario);

		return ResponseEntity.ok().build();
	}

}
