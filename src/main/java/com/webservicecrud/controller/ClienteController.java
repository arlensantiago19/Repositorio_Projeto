package com.webservicecrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.webservicecrud.dao.ClienteDAO;
import com.webservicecrud.model.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(value="API REST Clientes")
@CrossOrigin(origins="*")
public class ClienteController {
	
	@Autowired
	ClienteDAO clienteDAO;
	
	/*Salvar um cliente no database*/
	
	@PostMapping
	@ApiOperation(value="Salva um cliente na tabela")
	public Cliente createCliente(@Valid @RequestBody Cliente cli) {
		return clienteDAO.salvar(cli);
	}
	
	/*Listar todos os clientes*/
	
	@GetMapping
	@ApiOperation(value="Retorna a lista de clientes")
	public List<Cliente> listarTodosClientes() {
		return clienteDAO.findAll();
	}
	
	/*Buscar um cliente pelo id*/
	
	@GetMapping("/{id}")
	@ApiOperation(value="Retorna um cliente em espec�fico pelo Id")
	public ResponseEntity<Cliente> buscarClientePeloId(@PathVariable(value="id") Long cliid) {
		Cliente cli = clienteDAO.findOne(cliid);
		
		if(cli==null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(cli);
	}

	/*Atualizar um cliente pelo id*/
	
	@PutMapping("/{id}")
	@ApiOperation(value="Atualiza os dados de um cliente j� cadastrado na tabela")
	public ResponseEntity<Cliente> atualizaCliente(@PathVariable(value="id") Long cliid, @Valid @RequestBody Cliente cliDetalhes) {
		
		Cliente cli = clienteDAO.findOne(cliid);
		if(cli==null) {
			return ResponseEntity.notFound().build();
		}
		
		cli.setNome(cliDetalhes.getNome());
		cli.setId(cliDetalhes.getId());
		cli.setIdade(cliDetalhes.getIdade());
		
		Cliente atualizaCliente = clienteDAO.salvar(cli);
		return ResponseEntity.ok().body(atualizaCliente);
	}
	
	/*Deletar um cliente pelo id*/
	
//	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ApiOperation(value="Deleta um cliente da tabela")
	public ResponseEntity<Cliente> deletarCliente(@PathVariable(value="id") Long cliid) {
		
		Cliente cli = clienteDAO.findOne(cliid);
		if(cli==null) {
			return ResponseEntity.notFound().build();
		}
		
		clienteDAO.delete(cli);
		
		return ResponseEntity.ok().build();
	}
 	
}
