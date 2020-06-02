package br.cesed.si.bd2.projeto.models;

import java.sql.Date;

public class VendaGeral {

	private Integer nf;

	private Integer codItem;

	private Integer qtdItem;

	private Date dtVenda;

	private Integer matFunc;

	private Integer codCaixa;

	public Integer getNf() {
		return nf;
	}

	public void setNf(Integer nf) {
		this.nf = nf;
	}

	public Integer getCodItem() {
		return codItem;
	}

	public void setCodItem(Integer codItem) {
		this.codItem = codItem;
	}

	public Integer getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(Integer qtdItem) {
		this.qtdItem = qtdItem;
	}

	public Date getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(Date dtVenda) {
		this.dtVenda = dtVenda;
	}

	public Integer getMatFunc() {
		return matFunc;
	}

	public void setMatFunc(Integer matFunc) {
		this.matFunc = matFunc;
	}

	public Integer getCodCaixa() {
		return codCaixa;
	}

	public void setCodCaixa(Integer codCaixa) {
		this.codCaixa = codCaixa;
	}

	@Override
	public String toString() {
		return "Nota Fiscal " + nf + " | Data da Venda: " + dtVenda + "\n" + "Cód. Item " + codItem + " | Qtd " + qtdItem
				+ " | Mat. Vendedor " + matFunc + " | Caixa nº " + codCaixa + "\n";
	}

}
