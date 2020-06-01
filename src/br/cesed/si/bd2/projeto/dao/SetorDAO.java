package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.Setor;

public class SetorDAO {

	private Connection conn;

	public SetorDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(Setor s) throws SQLException {

		String sql = "INSERT INTO setor (codigo, nome) VALUES (?, ?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, s.getCodigo());
			pstm.setString(2, s.getNome());

			pstm.execute();

			System.out.println("\nSetor adicionado com sucesso.");
		}
	}

	public List<Setor> listAll() throws SQLException {

		List<Setor> listaSetores = new ArrayList<Setor>();

		String sql = "SELECT codigo, nome FROM setor ORDER BY codigo;";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Setor s = new Setor();
					s.setCodigo(rs.getInt("codigo"));
					s.setNome(rs.getString("nome"));

					listaSetores.add(s);
				}

			}
		}

		return listaSetores;

	}

	public List<Setor> listByName(String setorBuscado) throws SQLException {
		
		List<Setor> listaSetores = new ArrayList<Setor>();
		
		String sql = "SELECT codigo, nome FROM setor WHERE nome LIKE '" + setorBuscado + "%' ORDER BY codigo";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Setor s = new Setor();
					s.setCodigo(rs.getInt("codigo"));
					s.setNome(rs.getString("nome"));
					
					listaSetores.add(s);
				}
				
			}
		}
		
		return listaSetores;
		
	}

	public void update(Setor setor) throws SQLException {
		
		String sql = "UPDATE setor SET nome = ? WHERE codigo = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, setor.getNome());
			pstm.setInt(2, setor.getCodigo());
			
			pstm.execute();
			
			System.out.println("Setor atualizado com sucesso.");
		}
	}


}
