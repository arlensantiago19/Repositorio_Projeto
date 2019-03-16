package com.webservicecrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Cliente;
import com.webservicecrud.repository.ClienteRepository;

@Service
public class ClienteDAO {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	/*Para salvar um cliente*/
	
	public Cliente salvar(Cliente cli) {
		return clienteRepository.save(cli);
	}
	
	/*Para listar todos os clientes*/
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	/*Procurar um cliente por id*/
	
	public Cliente findOne(Long cliid) {
		return clienteRepository.findOne(cliid);
	}
	
	/*Deletar um cliente*/
	
	public void delete(Cliente cli) {
		clienteRepository.delete(cli);
	}

}
