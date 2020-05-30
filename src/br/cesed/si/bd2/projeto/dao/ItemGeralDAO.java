package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.Item;

public class ItemGeralDAO {

	private Connection conn;

	public ItemGeralDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(Item item) throws SQLException {

		String sql = "INSERT INTO item (cod_barra, nome, preco, cod_setor, qtd) VALUES (?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, item.getCodBarra());
			pstm.setString(2, item.getNome());
			pstm.setDouble(3, item.getPreco());
			pstm.setInt(4, item.getCodSetor());
			pstm.setInt(5, item.getQuantidade());

			pstm.execute();

			System.out.println("\nItem Geral adicionado com sucesso.");
		}
	}

	public List<Item> listAll() throws SQLException {

		List<Item> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, garant_loj, garant_fab, qtd FROM item";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Item i = new Item();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setGarantiaLoja(rs.getInt("garant_loj"));
					i.setGarantiaFabricante(rs.getInt("garant_fab"));
					i.setQuantidade(rs.getInt("qtd"));

					listItems.add(i);
				}
			}
		}

		return listItems;
	}

	public List<Item> listByDescricao(String descricao) throws SQLException {

		List<Item> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, garant_loj, garant_fab, qtd FROM item WHERE nome LIKE '"
				+ descricao + "%'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Item i = new Item();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setGarantiaLoja(rs.getInt("garant_loj"));
					i.setGarantiaFabricante(rs.getInt("garant_fab"));
					i.setQuantidade(rs.getInt("qtd"));

					listItems.add(i);
				}
			}
		}

		return listItems;

	}

	public List<Item> listBySetor(String nomeSetor) throws SQLException {
		
		List<Item> listItems = new ArrayList<>();
		
		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, garant_loj, garant_fab, qtd FROM item i, setor s WHERE i.cod_setor =  s.codigo AND s.nome LIKE '"
				+ nomeSetor + "%'";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Item i = new Item();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setGarantiaLoja(rs.getInt("garant_loj"));
					i.setGarantiaFabricante(rs.getInt("garant_fab"));
					i.setQuantidade(rs.getInt("qtd"));
					
					listItems.add(i);
				}
			}
		}
		
		return listItems;
		
	}

	public List<Item> listByEstoque() throws SQLException {
		
		List<Item> listItems = new ArrayList<>();
		
		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, garant_loj, garant_fab, qtd FROM item WHERE qtd = 0";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Item i = new Item();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setGarantiaLoja(rs.getInt("garant_loj"));
					i.setGarantiaFabricante(rs.getInt("garant_fab"));
					i.setQuantidade(rs.getInt("qtd"));
					
					listItems.add(i);
				}
			}
		}
		
		return listItems;
		
	}

}
