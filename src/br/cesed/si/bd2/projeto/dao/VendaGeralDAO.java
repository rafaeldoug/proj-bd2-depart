package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.VendaGeral;

public class VendaGeralDAO {

	private Connection conn;

	public VendaGeralDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(VendaGeral vg) throws SQLException {

		String sql = "INSERT INTO venda (nt_fiscal, cod_item, qtd_item, dt_venda, mat_func, cod_caixa) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, vg.getNf());
			pstm.setInt(2, vg.getCodItem());
			pstm.setInt(3, vg.getQtdItem());
			pstm.setDate(4, vg.getDtVenda());
			pstm.setInt(5, vg.getMatFunc());
			pstm.setInt(6, vg.getCodCaixa());

			pstm.execute();

			System.out.println("\nVenda adicionada com sucesso.\n");

		}
	}

	public List<VendaGeral> listAll() throws SQLException {

		List<VendaGeral> listVendaGeral = new ArrayList<>();

		String sql = "SELECT nt_fiscal, cod_item, qtd_item, dt_venda, mat_func, cod_caixa FROM venda , item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND s.nome != 'ED' AND s.nome != 'EE'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaGeral vg = new VendaGeral();
					vg.setNf(rs.getInt("nt_fiscal"));
					vg.setCodItem(rs.getInt("cod_item"));
					vg.setQtdItem(rs.getInt("qtd_item"));
					vg.setDtVenda(rs.getDate("dt_venda"));
					vg.setMatFunc(rs.getInt("mat_func"));
					vg.setCodCaixa(rs.getInt("cod_caixa"));

					listVendaGeral.add(vg);
				}
			}
		}

		return listVendaGeral;

	}

	public List<VendaGeral> listByDate(Date inicio, Date fim) throws SQLException {

		List<VendaGeral> listVendaGeral = new ArrayList<>();

		String sql = "SELECT nt_fiscal, cod_item, qtd_item, dt_venda, mat_func, cod_caixa FROM venda , item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND s.nome != 'ED' AND s.nome != 'EE' AND dt_venda BETWEEN '"
				+ inicio + "' AND '" + fim + "'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaGeral vg = new VendaGeral();
					vg.setNf(rs.getInt("nt_fiscal"));
					vg.setCodItem(rs.getInt("cod_item"));
					vg.setQtdItem(rs.getInt("qtd_item"));
					vg.setDtVenda(rs.getDate("dt_venda"));
					vg.setMatFunc(rs.getInt("mat_func"));
					vg.setCodCaixa(rs.getInt("cod_caixa"));

					listVendaGeral.add(vg);
				}
			}
		}

		return listVendaGeral;
	}

	public List<VendaGeral> listByMatricFunc(int matricula) throws SQLException {

		List<VendaGeral> listVendaGeral = new ArrayList<>();

		String sql = "SELECT nt_fiscal, cod_item, qtd_item, dt_venda, mat_func, cod_caixa FROM venda , item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND s.nome != 'ED' AND s.nome != 'EE' AND mat_func = "
				+ matricula;

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaGeral vg = new VendaGeral();
					vg.setNf(rs.getInt("nt_fiscal"));
					vg.setCodItem(rs.getInt("cod_item"));
					vg.setQtdItem(rs.getInt("qtd_item"));
					vg.setDtVenda(rs.getDate("dt_venda"));
					vg.setMatFunc(rs.getInt("mat_func"));
					vg.setCodCaixa(rs.getInt("cod_caixa"));

					listVendaGeral.add(vg);
				}
			}
		}

		return listVendaGeral;
	}

	public List<VendaGeral> listByNomeFunc(String nome) throws SQLException {

		List<VendaGeral> listVendaGeral = new ArrayList<>();

		String sql = "SELECT v.nt_fiscal, v.cod_item, v.qtd_item, v.dt_venda, v.mat_func, v.cod_caixa FROM venda v, funcionario f, item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND s.nome != 'ED' AND s.nome != 'EE' AND v.mat_func = f.matricula AND f.nome LIKE '"
				+ nome + "%'"; 

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaGeral vg = new VendaGeral();
					vg.setNf(rs.getInt("nt_fiscal"));
					vg.setCodItem(rs.getInt("cod_item"));
					vg.setQtdItem(rs.getInt("qtd_item"));
					vg.setDtVenda(rs.getDate("dt_venda"));
					vg.setMatFunc(rs.getInt("mat_func"));
					vg.setCodCaixa(rs.getInt("cod_caixa"));

					listVendaGeral.add(vg);
				}
			}
		}

		return listVendaGeral;
	}

	public void update(VendaGeral vg) throws SQLException {

		String sql = "UPDATE venda SET qtd_item = ?, mat_func = ?, cod_caixa = ? WHERE nt_fiscal = ? AND cod_item = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, vg.getQtdItem());
			pstm.setInt(2, vg.getMatFunc());
			pstm.setInt(3, vg.getCodCaixa());
			pstm.setInt(4, vg.getNf());
			pstm.setInt(5, vg.getCodItem());

			pstm.execute();

			System.out.println("\nVenda atualizada com sucesso.\n");

		}
	}

	public void delete(int nf, int codItem) throws SQLException {

		String sql = "DELETE FROM venda WHERE nt_fiscal = " + nf + " AND cod_item = " + codItem;

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			System.out.println("\nVenda removida com sucesso.\n");

		}
	}

}
