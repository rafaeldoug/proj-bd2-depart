package br.cesed.si.bd2.projeto.models;

public class Setor {

	private Integer codigo;

	private String nome;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Setor " + codigo + " | " + nome;
	}

}
