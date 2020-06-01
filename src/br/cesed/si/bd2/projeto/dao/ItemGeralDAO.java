package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.ItemGeral;

public class ItemGeralDAO {

	private Connection conn;

	public ItemGeralDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(ItemGeral item) throws SQLException {

		String sql = "INSERT INTO item (cod_barra, nome, preco, cod_setor, validade, qtd) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, item.getCodBarra());
			pstm.setString(2, item.getNome());
			pstm.setDouble(3, item.getPreco());
			pstm.setInt(4, item.getCodSetor());
			pstm.setDate(5, item.getValidade());
			pstm.setInt(6, item.getQuantidade());

			pstm.execute();

			System.out.println("\nItem Geral adicionado com sucesso.");
		}
	}

	public List<ItemGeral> listAll() throws SQLException {

		List<ItemGeral> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, qtd FROM item";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemGeral i = new ItemGeral();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setQuantidade(rs.getInt("qtd"));

					listItems.add(i);
				}
			}
		}

		return listItems;
	}

	public List<ItemGeral> listByDescricao(String descricao) throws SQLException {

		List<ItemGeral> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, qtd FROM item WHERE nome LIKE '" + descricao
				+ "%'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemGeral i = new ItemGeral();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setQuantidade(rs.getInt("qtd"));

					listItems.add(i);
				}
			}
		}

		return listItems;

	}

	public List<ItemGeral> listBySetor(String nomeSetor) throws SQLException {

		List<ItemGeral> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, qtd FROM item i, setor s WHERE i.cod_setor =  s.codigo AND s.nome LIKE '"
				+ nomeSetor + "%'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemGeral i = new ItemGeral();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setQuantidade(rs.getInt("qtd"));

					listItems.add(i);
				}
			}
		}

		return listItems;

	}

	public List<ItemGeral> listByEstoque() throws SQLException {

		List<ItemGeral> listItems = new ArrayList<>();

		String sql = "SELECT cod_barra, nome, preco, validade, cod_setor, qtd FROM item WHERE qtd = 0";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					ItemGeral i = new ItemGeral();
					i.setCodBarra(rs.getInt("cod_barra"));
					i.setNome(rs.getString("nome"));
					i.setPreco(rs.getDouble("preco"));
					i.setValidade(rs.getDate("validade"));
					i.setCodSetor(rs.getInt("cod_setor"));
					i.setQuantidade(rs.getInt("qtd"));

					listItems.add(i);
				}
			}
		}

		return listItems;

	}

	public void update(ItemGeral item) throws SQLException {

		String sql = "UPDATE item SET nome = ?, preco = ?, cod_setor = ? WHERE cod_barra = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, item.getNome());
			pstm.setDouble(2, item.getPreco());
			pstm.setInt(3, item.getCodSetor());
			pstm.setInt(4, item.getCodBarra());

			pstm.execute();

			System.out.println("\nItem Geral alterado com sucesso.");

		}
	}

	public void updateComValidade(ItemGeral item) throws SQLException {

		String sql = "UPDATE item SET nome = ?, preco = ?, validade = ?, cod_setor = ? WHERE cod_barra = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, item.getNome());
			pstm.setDouble(2, item.getPreco());
			pstm.setDate(3, item.getValidade());
			pstm.setInt(4, item.getCodSetor());
			pstm.setInt(5, item.getCodBarra());

			pstm.execute();

			System.out.println("\nItem Geral alterado com sucesso.");

		}
	}

	public void updateQuantidade(ItemGeral item) throws SQLException {

		String sql = "UPDATE item SET qtd = ? WHERE cod_barra = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, item.getQuantidade());
			pstm.setInt(2, item.getCodBarra());

			pstm.execute();

			System.out.println("\nQuantidade do Item Geral alterado com sucesso.");

		}
	}

}
