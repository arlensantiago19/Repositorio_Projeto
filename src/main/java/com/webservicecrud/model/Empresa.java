package com.webservicecrud.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
 
@Entity
@Table(name = "Empresas")
@EntityListeners(AuditingEntityListener.class)

public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@Column(unique=true, nullable=false) 
	private String cnpj;

	@Column
	private String inscricao;

	@Column
	private String endereco;

	@Column(unique=true, nullable=false) 
	private String email;

	@OneToOne (cascade=CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="codendcorresp", unique= true, nullable=true, insertable=true, updatable=true)
	private Endereco endCorrespondencia;


	@Column(updatable = false, nullable=false)
	@CreationTimestamp
	private Timestamp dt_cadastro;

	public String getDt_cadastro() {
		if (dt_cadastro == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataFormatada = sdf.format(dt_cadastro);

		return dataFormatada;

	}

	public void setDt_cadastro(Timestamp dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndCorrespondencia() {
		return endCorrespondencia;
	}

	public void setEndCorrespondencia(Endereco endCorrespondencia) {
		this.endCorrespondencia = endCorrespondencia;
	}

	
}
