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
@Table(name = "Enderecos")
@EntityListeners(AuditingEntityListener.class)

public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codendereco;

	@Column(nullable = false)
	private Long codcidade;

	@Column(nullable = false)
	private String bairro;

	@Column(nullable = false)
	private String logradouro;

	@Column
	private String complemento;

	@Column
	private Long numero;

	@Column(nullable = false)
	private String cep;

	private Double latitude;

	private Double longitude;

	public Long getCodendereco() {
		return codendereco;
	}

	public void setCodendereco(Long codendereco) {
		this.codendereco = codendereco;
	}

	public Long getCodcidade() {
		return codcidade;
	}

	public void setCodcidade(Long codcidade) {
		this.codcidade = codcidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


}
