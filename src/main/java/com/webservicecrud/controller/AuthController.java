package com.webservicecrud.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.dao.UsuarioDAO;
import com.webservicecrud.model.Usuario;
import com.webservicecrud.services.EncryptionService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class AuthController {

	EntityManagerFactory emf;
	EntityManager em;

	@Autowired
	UsuarioDAO usuarioDAO;

	public AuthController(UsuarioDAO usuarioDAO) {
		super();
		this.usuarioDAO = usuarioDAO;
	}

	/* Autentica o usuário mediante senha */

	@GetMapping
	public Response login(@RequestHeader(value = "usuario") String usuario,
			@RequestHeader(value = "senha") String senhaRequisicao) {

		// vai na tabela do banco de dados através de um objeto Usuario e dá um findOne
		// nele para pegar a senha
		System.out.println("usuario: " + usuario);
		System.out.println("senha: " + senhaRequisicao);
		java.util.Date date = new Date();
		String dateFormatada = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date hora = Calendar.getInstance().getTime();
		String horaFormatada = sdf.format(hora);
		
		System.out.println("Usuário acessou em:" + " " + dateFormatada + " às " + horaFormatada);

		Usuario user = usuarioDAO.findByUsuario(usuario);
		if (user == null) {
			System.out.println("ERRO: Usuário não localizado.");
			return Response.status(HttpStatus.UNAUTHORIZED.value()).build();
		}
		String senhaCadastrada = user.getSenha();

		// Se não encontrar ou a senha não bater, retornar não autorizado
		if (!EncryptionService.criptografar(senhaRequisicao).equals(senhaCadastrada)) {
			return Response.status(HttpStatus.UNAUTHORIZED.value()).build();
		}
		System.out.println("INFO: Usuário logado com sucesso.");

		return Response.ok().build();
	}

}
