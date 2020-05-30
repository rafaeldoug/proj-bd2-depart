package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.enums.TipoCaixa;
import br.cesed.si.bd2.projeto.models.Caixa;

public class CaixaDAO {

	private Connection conn;

	public CaixaDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(Caixa caixa) throws SQLException {

		String sql = "INSERT INTO caixa (numero, tipo) VALUES (?, ?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, caixa.getNumero());
			pstm.setString(2, caixa.getTipo().toString().toLowerCase());

			pstm.execute();

			System.out.println("\nCaixa adicionado com sucesso.");
		}
	}

	public List<Caixa> listAll() throws SQLException {

		List<Caixa> listCaixas = new ArrayList<>();

		String sql = "SELECT numero, tipo FROM caixa ORDER BY numero";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Caixa c = new Caixa();
					c.setNumero(rs.getInt("numero"));
					TipoCaixa tc = TipoCaixa.valueOf(rs.getString("tipo").toUpperCase());
					c.setTipo(tc);

					listCaixas.add(c);
				}
			}
		}

		return listCaixas;
	}

	public void updateCaixa(Caixa caixa) throws SQLException {

		String sql = "UPDATE caixa SET tipo = ? WHERE numero = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, caixa.getTipo().toString().toLowerCase());
			pstm.setInt(2, caixa.getNumero());

			pstm.execute();

			System.out.println("\nCaixa atualizado com sucesso.");
		}

	}

	public void delete(int numero) throws SQLException {

		String sql = "DELETE FROM caixa WHERE numero = " + numero;

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
		}

	}

}
