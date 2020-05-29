package br.cesed.si.bd2.projeto.models;

import java.sql.Date;

public class Item {

	private Integer codBarra;

	private String nome;

	private Double preco;

	private Date validade;

	private Integer codSetor;

	private Integer garantiaLoja;

	private Integer garantiaFabricante;

	private int quantidade;

	public Integer getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(Integer codBarra) {
		this.codBarra = codBarra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Integer getCodSetor() {
		return codSetor;
	}

	public void setCodSetor(Integer codSetor) {
		this.codSetor = codSetor;
	}

	public Integer getGarantiaLoja() {
		return garantiaLoja;
	}

	public void setGarantiaLoja(Integer garantiaLoja) {
		this.garantiaLoja = garantiaLoja;
	}

	public Integer getGarantiaFabricante() {
		return garantiaFabricante;
	}

	public void setGarantiaFabricante(Integer garantiaFabricante) {
		this.garantiaFabricante = garantiaFabricante;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Cód. de Barras " + codBarra + "\nDescricao " + nome + " | Preço R$" + String.format("%.2f", preco)
				+ "\n" + "Setor " + codSetor + " | Quantidade disponível " + quantidade + "\n" + "Validade "
				+ (validade != null ? validade : "N/A") + " | Garantia Loja "
				+ (garantiaLoja == 0 ? "N/A" : garantiaLoja) + " | Garantia Fabricante "
				+ (garantiaFabricante == 0 ? "N/A" : garantiaFabricante) + "\n";

	}

}
