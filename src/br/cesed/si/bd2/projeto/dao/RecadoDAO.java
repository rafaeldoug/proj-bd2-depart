package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.Recado;

public class RecadoDAO {

	private Connection conn;

	public RecadoDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(Recado recado) throws SQLException {

		String sql = "SELECT inserirRecado(?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, recado.getMensagem());

			pstm.execute();

			System.out.println();
			System.out.println("Recado adicionado com sucesso.");
		}
	}

	public List<Recado> listAll() throws SQLException {
		List<Recado> listRecados = new ArrayList<>();

		String sql = "SELECT indice, mensagem FROM recado ORDER BY indice";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Recado r = new Recado();
					r.setIndice(rs.getInt("indice"));
					r.setMensagem(rs.getString("mensagem"));

					listRecados.add(r);

				}
			}
		}

		return listRecados;
	}
	
	public void update(Recado recado) throws SQLException {
		
		String sql = "UPDATE recado SET mensagem = ? WHERE indice = ?";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime dt = LocalDateTime.now();
			String date = dt.format(dtf);
			String msg =  date + " " + recado.getMensagem();
			pstm.setString(1, msg);
			pstm.setInt(2, recado.getIndice());
			
			pstm.execute();
			
			System.out.println("Recado alterado com sucesso.");
		}
	}

	public void delete(Integer indice) throws SQLException {

		String sql = "DELETE FROM recado WHERE indice = " + indice;

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			
			System.out.println("Recado deletado com sucesso.");
		}

	}
	
	public void deleteAll() throws SQLException {
		
		String sql = "DELETE FROM recado";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			
			System.out.println("Todos os recados removidos com sucesso.");
		}
		
	}

}
