package com.webservicecrud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservicecrud.model.Empresa;
import com.webservicecrud.repository.EmpresaRepository;

@Service
public class EmpresaDAO { 

	@Autowired
	EmpresaRepository empresaRepository;

	/* Para salvar um empresa */

	public Empresa save(Empresa empresa) {
		return empresaRepository.save(empresa);
	}

	/* Para listar todos os empresas */

	public Iterable<Empresa> findAll() {
		return empresaRepository.findAll();
	}

	/* Procurar um empresa por id */

	public Empresa findOne(Long id) {
		return empresaRepository.findOne(id);
	}

	/* Deletar um empresa */

	public void delete(Empresa empresa) {
		empresaRepository.delete(empresa);
	}

	/* Este findOne foi feito para o authController fazer a verificação da senha com o empresa */
	
	public Empresa findByInscricao(String inscricao) {
		return empresaRepository.findByInscricao(inscricao);
	}

	public Empresa findByEmail(String email) {
		return empresaRepository.findByEmail(email);
	}
	
	public Empresa findByCnpj(String cnpj) {
		return empresaRepository.findByCnpj(cnpj);
	}

}
