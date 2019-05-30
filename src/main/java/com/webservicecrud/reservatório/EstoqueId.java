package com.webservicecrud.reservatório;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class EstoqueId implements Serializable {

	private Long codempresa;
	private Long codlocal;
	private Long codprod;

	public EstoqueId() {
	}

	public EstoqueId(Long codempresa, Long codlocal, Long codprod) {

		this.codempresa = codempresa;
		this.codlocal = codlocal;
		this.codprod = codprod;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codempresa == null) ? 0 : codempresa.hashCode());
		result = prime * result + ((codlocal == null) ? 0 : codlocal.hashCode());
		result = prime * result + ((codprod == null) ? 0 : codprod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstoqueId other = (EstoqueId) obj;
		if (codempresa == null) {
			if (other.codempresa != null)
				return false;
		} else if (!codempresa.equals(other.codempresa))
			return false;
		if (codlocal == null) {
			if (other.codlocal != null)
				return false;
		} else if (!codlocal.equals(other.codlocal))
			return false;
		if (codprod == null) {
			if (other.codprod != null)
				return false;
		} else if (!codprod.equals(other.codprod))
			return false;
		return true;
	}

}
