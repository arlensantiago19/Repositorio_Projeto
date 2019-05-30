package com.webservicecrud.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="Fornecedores")
@EntityListeners(AuditingEntityListener.class)

public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codfornecedor;
	
	private String nome;

	public Long getCodfornecedor() {
		return codfornecedor;
	}

	public void setCodfornecedor(Long codfornecedor) {
		this.codfornecedor = codfornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
