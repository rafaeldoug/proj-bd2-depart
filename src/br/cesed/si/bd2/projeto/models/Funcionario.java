package br.cesed.si.bd2.projeto.models;

import java.sql.Date;

public class Funcionario {

	private Integer matricula;

	private Integer cpf;

	private String nome;

	private Double salario;

	private String funcao;

	private String classificacao;

	private Date dtAdmissao;

	private Date dtDemissao;

	private String motivo;

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Date getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(Date dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public Date getDtDemissao() {
		return dtDemissao;
	}

	public void setDtDemissao(Date dtDemissao) {
		this.dtDemissao = dtDemissao;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	private String checkAttrib(String data) {
		String attrib = data != null ? data : "N/A";
		return attrib;
	}

	@Override
	public String toString() {
		return "Matricula " + matricula + " | CPF  " + cpf + "\nNome: " + nome + " | Funcao: "
				+ checkAttrib(funcao) + " | Salario R$" + String.format("%.2f", (salario != null ? salario : "N/A"))
				+ " | Classificacao: " + checkAttrib(classificacao) + "\nData de Admissao "
				+ (dtAdmissao != null ? dtAdmissao : "N/A") + "\nData de Demissao "
				+ (dtDemissao != null ? dtDemissao : "N/A") + " <-> Motivo: " + checkAttrib(motivo) + "\n";
	}

}
