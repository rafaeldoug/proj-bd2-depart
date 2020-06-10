package br.cesed.si.bd2.projeto.models;

public class ItemEletro {

	private Integer codBarra;

	private String nome;

	private Double preco;

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
		return "Cód. de Barras " + codBarra + "\nDescricao: " + nome + " | Preço R$" + String.format("%.2f", preco)
				+ "\n" + "Setor " + codSetor + " | Quantidade disponível: " + quantidade + "\n" + "Garantia Loja "
				+ (garantiaLoja != null ? garantiaLoja : "N/A") + " ano(s)" + " | Garantia Fabricante "
				+ (garantiaFabricante != null ? garantiaFabricante : "N/A") + " ano(s)" + "\n";

	}
}
