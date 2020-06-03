package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.FuncSalPeriod;

public class FuncSalPeriodDAO {

	private Connection conn;

	public FuncSalPeriodDAO(Connection connection) {
		this.conn = connection;
	}

	public List<FuncSalPeriod> calcSalario(Date inicio, Date fim) throws SQLException {

		List<FuncSalPeriod> listSal = new ArrayList<>();

		String sql = "SELECT calculaSalario('" + inicio + "', '" + fim + "')";
		String sql2 = "SELECT nome, matricula, funcao, salario_periodo FROM tmp_FuncSalPeriod";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (PreparedStatement pstm2 = conn.prepareStatement(sql2)) {
				pstm2.execute();

				try (ResultSet rs = pstm2.getResultSet()) {
					while (rs.next()) {
						FuncSalPeriod fsp = new FuncSalPeriod();
						fsp.setNome(rs.getString("nome"));
						fsp.setMatricula(rs.getInt("matricula"));
						fsp.setFuncao(rs.getString("funcao"));
						fsp.setSalario(rs.getDouble("salario_periodo"));

						listSal.add(fsp);
					}
				}
			}

		}

		return listSal;

	}

}
