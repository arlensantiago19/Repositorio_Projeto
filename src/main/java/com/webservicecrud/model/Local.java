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
@Table(name = "Locais")
@EntityListeners(AuditingEntityListener.class)

public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codlocal;

	@Column(nullable = false)
	private Long codendereco;

	@Column(nullable = false)
	private String status;

	public Long getCodlocal() {
		return codlocal;
	}

	public void setCodlocal(Long codlocal) {
		this.codlocal = codlocal;
	}

	public Long getCodendereco() {
		return codendereco;
	}

	public void setCodendereco(Long codendereco) {
		this.codendereco = codendereco;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
