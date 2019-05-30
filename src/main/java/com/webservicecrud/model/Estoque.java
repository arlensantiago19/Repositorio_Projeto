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
@Table(name = "Estoques")
@EntityListeners(AuditingEntityListener.class)

public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codestoque;

	@Column(nullable = false)
	private Long codempresa;

	@Column(nullable = false)
	private Long codlocal;

	@Column(nullable = false)
	private Long codprod;

	@Column(nullable = false)
	private Long quantidade;

	@Column(nullable = false) //0 - inativo, 1 - ativo
	private Integer status;

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

	public Long getCodempresa() {
		return codempresa;
	}

	public void setCodempresa(Long codempresa) {
		this.codempresa = codempresa;
	}

	public Long getCodlocal() {
		return codlocal;
	}

	public void setCodlocal(Long codlocal) {
		this.codlocal = codlocal;
	}

	public Long getCodprod() {
		return codprod;
	}

	public void setCodprod(Long codprod) {
		this.codprod = codprod;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
