package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.ItemEletro;

public class ItemEletroDAO {

	private Connection conn;

	public ItemEletroDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(ItemEletro item) throws SQLException {

		String sql = "INSERT INTO item (cod_barra, nome, preco, cod_setor, garant_loj, garant_fab, qtd) VALUES (?,?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, item.getCodBarra());
			pstm.setString(2, item.getNome());
			pstm.setDouble(3, item.getPreco());
			pstm.setInt(4, item.getCodSetor());
			pstm.setInt(5, item.getGarantiaLoja());
			pstm.setInt(6, item.getGarantiaFabricante());
			pstm.setInt(7, item.getQuantidade());

			pstm.execute();

			System.out.println("\nItem Eletro adicionado com sucesso.");
		}
	}

	public List<ItemEletro> listAll() throws SQLException {

		List<ItemEletro> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, cod_setor, garant_loj, garant_fab, qtd FROM item WHERE cod_setor = 1 OR cod_setor = 2";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemEletro i = new ItemEletro();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
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

	public List<ItemEletro> listByDescricao(String descricao) throws SQLException {

		List<ItemEletro> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, cod_setor, garant_loj, garant_fab, qtd FROM item WHERE nome LIKE  '"
				+ descricao + "%' AND cod_setor = 1 OR cod_setor = 2";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemEletro i = new ItemEletro();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
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

	public List<ItemEletro> listByEstoque() throws SQLException {

		List<ItemEletro> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, qtd FROM item WHERE qtd = 0 AND cod_setor = 1 OR cod_setor = 2";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemEletro i = new ItemEletro();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
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

	public void update(ItemEletro item) throws SQLException {

		String sql = "UPDATE item SET nome = ?, preco = ?, cod_setor = ?, garant_loj = ?, garant_fab = ? WHERE cod_barra = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, item.getNome());
			pstm.setDouble(2, item.getPreco());
			pstm.setInt(3, item.getCodSetor());
			pstm.setInt(4, item.getGarantiaLoja());
			pstm.setInt(5, item.getGarantiaFabricante());
			pstm.setInt(6, item.getCodBarra());

			pstm.execute();

			System.out.println("\nItem Eletro alterado com sucesso.");

		}

	}

	public void updateQuantidade(ItemEletro item) throws SQLException {
		
		String sql = "UPDATE item SET qtd = ? WHERE cod_barra = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, item.getQuantidade());
			pstm.setInt(2, item.getCodBarra());
			
			pstm.execute();
			
			System.out.println("\nQuatidade do Item Eletro alterado com sucesso.");
			
		}
		
	}
	


}
