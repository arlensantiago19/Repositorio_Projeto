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
@Table(name = "Tipo_Valor")
@EntityListeners(AuditingEntityListener.class)

public class TipoValor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tipo_valor;

	@Column
	private String descricao;

	// 0 - String
	// 1 - Long
	// 2 - Double
	// 3 - Timestamp
	@Column
	private Long tipo;

	public Long getTipo_valor() {
		return tipo_valor;
	}

	public void setTipo_valor(Long tipo_valor) {
		this.tipo_valor = tipo_valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}


	
}
