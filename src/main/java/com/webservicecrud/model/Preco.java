package com.webservicecrud.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Precos")
@EntityListeners(AuditingEntityListener.class)

public class Preco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codpreco;

	@Column(nullable = false)
	private Long codestoque;

	@Column(nullable = false)
	private Double preco;

	private Long quantidadeAtacado;
	
	private Double precoAtacado;
	
	@Column(nullable = false)
	private Timestamp vigencia_inicio;

	@Column(nullable = false)
	private Timestamp vigencia_fim;

	@Column(nullable = false) //0 - inativo, 1 - ativo
	private Integer status;

	public Long getCodpreco() {
		return codpreco;
	}

	public void setCodpreco(Long codpreco) {
		this.codpreco = codpreco;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Timestamp getVigencia_inicio() {
		return vigencia_inicio;
	}

	public void setVigencia_inicio(Timestamp vigencia_inicio) {
		this.vigencia_inicio = vigencia_inicio;
	}

	public Timestamp getVigencia_fim() {
		return vigencia_fim;
	}

	public void setVigencia_fim(Timestamp vigencia_fim) {
		this.vigencia_fim = vigencia_fim;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCodestoque() {
		return codestoque;
	}

	public void setCodestoque(Long codestoque) {
		this.codestoque = codestoque;
	}

	public Long getQuantidadeAtacado() {
		return quantidadeAtacado;
	}

	public void setQuantidadeAtacado(Long quantidadeAtacado) {
		this.quantidadeAtacado = quantidadeAtacado;
	}

	public Double getPrecoAtacado() {
		return precoAtacado;
	}

	public void setPrecoAtacado(Double precoAtacado) {
		this.precoAtacado = precoAtacado;
	}

	
}
