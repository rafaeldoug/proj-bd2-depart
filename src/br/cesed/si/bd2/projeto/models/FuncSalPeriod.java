package br.cesed.si.bd2.projeto.models;

public class FuncSalPeriod {

	private String nome;

	private Integer matricula;

	private String funcao;

	private Double salario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + " | Matricula: " + matricula + " | Funcao:" + funcao + " | Salario Total R$"
				+ String.format("%.2f", salario);
	}

}
