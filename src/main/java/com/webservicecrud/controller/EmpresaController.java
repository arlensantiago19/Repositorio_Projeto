package com.webservicecrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservicecrud.dao.EmpresaDAO;
import com.webservicecrud.model.Empresa;
import com.webservicecrud.model.Endereco;
import com.webservicecrud.services.ValidaCNPJ;

@RestController
@RequestMapping("/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

	@Autowired
	EmpresaDAO empresaDAO;

	public EmpresaController(EmpresaDAO empresaDAO) {
		super();
		this.empresaDAO = empresaDAO;
	}

	/* Salvar uma empresa no database */

	@PostMapping
	public ResponseEntity<Empresa> createEmpresa(@Valid @RequestBody Empresa empresa) {
		Empresa emp;

		// Validar o CNPJ da entrada
		String cnpj = empresa.getCnpj();
		if (!ValidaCNPJ.isCNPJ(cnpj)) {
			System.out.println("ERRO: CNPJ inválido! " + cnpj);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println("INFO: CNPJ válido! " + cnpj);
		cnpj = ValidaCNPJ.imprimeCNPJSemMascara(cnpj);
		empresa.setCnpj(cnpj);

		emp = empresaDAO.findByCnpj(cnpj);
		if (emp != null) {
			System.out.println("ERRO: CNPJ já em uso.");
			return ResponseEntity.status(HttpStatus.FOUND).build();
		}

		empresa = empresaDAO.save(empresa);
		return ResponseEntity.ok().body(empresa);
	}

	/* Listar todos os empresas */

	@GetMapping
	public Iterable<Empresa> listarTodosEmpresas() {
		return empresaDAO.findAll();
	}

	/* Buscar um empresa pelo id */

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> buscarEmpresaPeloId(@PathVariable(value = "id") Long id) {
		Empresa empresa = empresaDAO.findOne(id);

		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(empresa);
	}

	/* Atualizar um empresa pelo id */

	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizaEmpresa(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Empresa empRequest) {

		Empresa empresa = empresaDAO.findOne(id);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}

		Empresa emp;

		System.out.println("INFO: Email " + empresa.getEmail() + " - " + empRequest.getEmail());
		if (!empRequest.getEmail().equals(empresa.getEmail())) {
			emp = empresaDAO.findByEmail(empRequest.getEmail());
			if (emp != null) {
				System.out.println("ERRO: Email já em uso.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			empresa.setEmail(empRequest.getEmail());
		}

		// Validar o CNPJ da entrada
		String cnpj = empRequest.getCnpj();
		if (!ValidaCNPJ.isCNPJ(cnpj)) {
			System.out.println("ERRO: CNPJ inválido! " + cnpj);
			return ResponseEntity.badRequest().build();
		}
		System.out.println("INFO: CNPJ válido! " + cnpj);
		String cnpjRequest = ValidaCNPJ.imprimeCNPJSemMascara(cnpj);
		String cnpjBase = empresa.getCnpj();
		if (!cnpjRequest.equals(cnpjBase)) {
			emp = empresaDAO.findByCnpj(cnpjRequest);
			if (emp != null) {
				System.out.println("ERRO: Cnpj já registrado.");
				return ResponseEntity.status(HttpStatus.FOUND).build();
			}
			empresa.setCnpj(cnpjRequest);
		}

		// Verificar se já tem endereço de correspondencia
		Endereco endereco = empresa.getEndCorrespondencia();

		// Verifica se tem um endereço no request
		Endereco endRequest = empRequest.getEndCorrespondencia();

		Long codendereco = null;
		if (endereco != null) { // tem end. corresp. na base
			if (endRequest != null) { // tem end. corresp. no request -> atualiza o end. corresp.
				codendereco = endereco.getCodendereco();
				endereco = endRequest;
				endereco.setCodendereco(codendereco);
			} else { // NÃO tem end. corresp. no request -> deleta o end.corresp.

				endereco = null;
			}
		} else { // NÃO tem end. corresp. na base
			if (endRequest != null) { // tem end. corresp. no request -> atualiza o end. corresp.
				endereco = endRequest;
				endereco.setCodendereco(null);
			} else {
				endereco = null;
			}
		}

		empresa.setEndCorrespondencia(endereco);

		empresa.setNome(empRequest.getNome());

		Empresa atualizaEmpresa = empresaDAO.save(empresa);
		return ResponseEntity.ok().body(atualizaEmpresa);
	}

	/* Deletar um empresa pelo id */

	@DeleteMapping("/{id}")
	public ResponseEntity<Empresa> deletarEmpresa(@PathVariable(value = "id") Long usuId) {

		Empresa empresa = empresaDAO.findOne(usuId);
		if (empresa == null) {
			return ResponseEntity.notFound().build();
		}

		empresaDAO.delete(empresa);

		return ResponseEntity.ok().build();
	}

}
