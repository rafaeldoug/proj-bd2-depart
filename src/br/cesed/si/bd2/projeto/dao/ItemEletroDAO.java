package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.cesed.si.bd2.projeto.models.ItemEletro;

public class ItemEletroDAO {
	
	private Connection conn;
	
	public ItemEletroDAO(Connection connection) {
		this.conn = connection;
	}
	
	public void save(ItemEletro item) throws SQLException {

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

}
