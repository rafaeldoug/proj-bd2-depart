package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.Funcionario;

public class FuncionarioDAO {

	private Connection conn;

	public FuncionarioDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(Funcionario funcionario) throws SQLException {

		String sql = "INSERT INTO funcionario (matricula, cpf, nome, salario, funcao, dt_admissao) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {

			pstm.setInt(1, funcionario.getMatricula());
			pstm.setInt(2, funcionario.getCpf());
			pstm.setString(3, funcionario.getNome());
			pstm.setDouble(4, funcionario.getSalario());
			pstm.setString(5, funcionario.getFuncao());
			pstm.setDate(6, funcionario.getDtAdmissao());

			pstm.execute();

			System.out.println("\nFuncionário adicionado com sucesso.\n");

		}
	}

	public List<Funcionario> listAll() throws SQLException {

		List<Funcionario> listaFunc = new ArrayList<Funcionario>();

		String sql = "SELECT matricula, cpf, nome, salario, funcao, classificacao, dt_admissao, dt_demissao, motivo_demissao FROM funcionario ORDER BY matricula;";
		classificaFuncionario();
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Funcionario f = new Funcionario();
					f.setMatricula(rs.getInt("matricula"));
					f.setCpf(rs.getInt("cpf"));
					f.setNome(rs.getString("nome"));
					f.setSalario(rs.getDouble("salario"));
					f.setFuncao(rs.getString("funcao"));
					f.setClassificacao(rs.getString("classificacao"));
					f.setDtAdmissao(rs.getDate("dt_admissao"));
					f.setDtDemissao(rs.getDate("dt_demissao"));
					f.setMotivo(rs.getString("motivo_demissao"));

					listaFunc.add(f);
				}
			}
		}

		return listaFunc;
	}

	public List<Funcionario> listByName(String nome) throws SQLException {

		List<Funcionario> listFunc = new ArrayList<>();

		String sql = "SELECT matricula, cpf, nome, salario, funcao, classificacao, dt_admissao, dt_demissao, motivo_demissao FROM funcionario WHERE nome LIKE '"
				+ nome + "%'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Funcionario f = new Funcionario();
					f.setMatricula(rs.getInt("matricula"));
					f.setCpf(rs.getInt("cpf"));
					f.setNome(rs.getString("nome"));
					f.setSalario(rs.getDouble("salario"));
					f.setFuncao(rs.getString("funcao"));
					f.setClassificacao(rs.getString("classificacao"));
					f.setDtAdmissao(rs.getDate("dt_admissao"));
					f.setDtDemissao(rs.getDate("dt_demissao"));
					f.setMotivo(rs.getString("motivo_demissao"));

					listFunc.add(f);
				}
			}
		}

		return listFunc;
	}

	public List<Funcionario> listByFuncao(String funcao) throws SQLException {

		List<Funcionario> listFunc = new ArrayList<>();

		String sql = "SELECT matricula, cpf, nome, salario, funcao, classificacao, dt_admissao, dt_demissao, motivo_demissao FROM funcionario WHERE funcao LIKE '"
				+ funcao + "%'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Funcionario f = new Funcionario();
					f.setMatricula(rs.getInt("matricula"));
					f.setCpf(rs.getInt("cpf"));
					f.setNome(rs.getString("nome"));
					f.setSalario(rs.getDouble("salario"));
					f.setFuncao(rs.getString("funcao"));
					f.setClassificacao(rs.getString("classificacao"));
					f.setDtAdmissao(rs.getDate("dt_admissao"));
					f.setDtDemissao(rs.getDate("dt_demissao"));
					f.setMotivo(rs.getString("motivo_demissao"));

					listFunc.add(f);
				}
			}
		}

		return listFunc;
	}

	public void classificaFuncionario() throws SQLException {

		String sql = "SELECT classificaFunc()";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			System.out.println("\nFuncionários classificados com sucesso.");

		}
	}

	public void updateSalario(Funcionario f) throws SQLException {

		String sql = "UPDATE funcionario SET salario = ? WHERE cpf = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setDouble(1, f.getSalario());
			pstm.setInt(2, f.getCpf());

			pstm.execute();

			System.out.println("\nDados alterados com sucesso.");
		}

	}

	public void saveDemissao(Funcionario f) throws SQLException {

		String sql = "UPDATE funcionario SET dt_demissao = ?, motivo_demissao = ? WHERE cpf = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setDate(1, f.getDtDemissao());
			pstm.setString(2, f.getMotivo());
			pstm.setInt(3, f.getCpf());

			pstm.execute();

			System.out.println("\nDados alterados com sucesso.");
		}

	}

}
