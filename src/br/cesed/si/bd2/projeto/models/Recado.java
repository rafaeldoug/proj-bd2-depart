package br.cesed.si.bd2.projeto.models;

public class Recado {

	private Integer indice;

	private String mensagem;

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Mensagem " + indice + "(" + mensagem + ")";
	}

}
