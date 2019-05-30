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
@Table(name = "Lotes")
@EntityListeners(AuditingEntityListener.class)

public class Lote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codlote;

	@Column
	private Long codestoque;

	@Column
	private Long quantidade;

	public Long getCodlote() {
		return codlote;
	}

	public void setCodlote(Long codlote) {
		this.codlote = codlote;
	}

	public Long getCodestoque() {
		return codestoque;
	}

	public void setCodestoque(Long codestoque) {
		this.codestoque = codestoque;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}


}
