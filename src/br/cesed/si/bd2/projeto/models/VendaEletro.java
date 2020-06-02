package br.cesed.si.bd2.projeto.models;

import java.sql.Date;

public class VendaEletro extends VendaGeral {

	private Date garantLoj;

	private Date garantFab;

	private Double comissaoVend;

	public VendaEletro() {
		super();
	}

	public Date getGarantLoj() {
		return garantLoj;
	}

	public void setGarantLoj(Date garantLoj) {
		this.garantLoj = garantLoj;
	}

	public Date getGarantFab() {
		return garantFab;
	}

	public void setGarantFab(Date garantFab) {
		this.garantFab = garantFab;
	}

	public Double getComissaoVend() {
		return comissaoVend;
	}

	public void setComissaoVend(Double comissaoVend) {
		this.comissaoVend = comissaoVend;
	}

}
