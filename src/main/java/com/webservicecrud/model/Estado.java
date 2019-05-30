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
@Table(name = "Estados")
@EntityListeners(AuditingEntityListener.class)

public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codestado;

	@Column(unique=true, nullable=false)
	private String estado;

	@Column(unique=true, nullable=false)
	private String uf;

	public Long getCodestado() {
		return codestado;
	}

	public void setCodestado(Long codestado) {
		this.codestado = codestado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
}
