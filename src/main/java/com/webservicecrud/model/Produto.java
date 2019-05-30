package com.webservicecrud.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "Produtos")
@EntityListeners(AuditingEntityListener.class)

public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codprod;

	@Column
	private String descricao;

	@Column
	private Long codfornecedor;
	
	private Long bla;

	@Column
	private String codigoBarras;

	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp dt_cadastro;

	@Column
	@OneToMany(mappedBy = "produto", targetEntity = ProdutoDetalhe.class, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ProdutoDetalhe> detalhes;

	public Long getCodprod() {
		return codprod;
	}

	public void setCodprod(Long codprod) {
		this.codprod = codprod;
	}

	public List<ProdutoDetalhe> getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(List<ProdutoDetalhe> detalhes) {
		this.detalhes = detalhes;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public Timestamp getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Timestamp dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public Long getCodfornecedor() {
		return codfornecedor;
	}

	public void setCodfornecedor(Long codfornecedor) {
		this.codfornecedor = codfornecedor;
	}

	
}
