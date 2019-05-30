package com.webservicecrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Cidades")
@EntityListeners(AuditingEntityListener.class)

public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codcidade;

	@Column(nullable=false)
	private String cidade;

	@Column(nullable=false)
	private Long codestado;

	public Long getCodcidade() {
		return codcidade;
	}

	public void setCodcidade(Long codcidade) {
		this.codcidade = codcidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCodestado() {
		return codestado;
	}

	public void setCodestado(Long codestado) {
		this.codestado = codestado;
	}

	
}
