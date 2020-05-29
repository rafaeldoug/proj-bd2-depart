package br.cesed.si.bd2.projeto.models;

import br.cesed.si.bd2.projeto.enums.TipoCaixa;

public class Caixa {

	private Integer numero;

	private TipoCaixa tipo;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoCaixa getTipo() {
		return tipo;
	}

	public void setTipo(TipoCaixa tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return "Caixa " + numero + " | " + tipo;
	}

}
