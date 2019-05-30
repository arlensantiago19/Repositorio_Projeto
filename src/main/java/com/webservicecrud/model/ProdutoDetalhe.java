package com.webservicecrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Produto_Detalhes")
@EntityListeners(AuditingEntityListener.class)

public class ProdutoDetalhe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String atributo;

	@Column
	private String valor;

	@Column
	private Long tipo_valor;

	@ManyToOne
	@JoinColumn(name = "codprod")
	private Produto produto;

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Long getTipo_valor() {
		return tipo_valor;
	}

	public void setTipo_valor(Long tipo_valor) {
		this.tipo_valor = tipo_valor;
	}

}
