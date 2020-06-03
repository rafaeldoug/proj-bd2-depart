package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cesed.si.bd2.projeto.models.VendaEletro;

public class VendaEletroDAO {

	private Connection conn;

	public VendaEletroDAO(Connection connection) {
		this.conn = connection;
	}

	public void save(VendaEletro ve) throws SQLException {

		String sql = "INSERT INTO venda (nt_fiscal, cod_item, qtd_item, dt_venda, mat_func, cod_caixa) VALUES (?,?,?,?,?,?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, ve.getNf());
			pstm.setInt(2, ve.getCodItem());
			pstm.setInt(3, ve.getQtdItem());
			pstm.setDate(4, ve.getDtVenda());
			pstm.setInt(5, ve.getMatFunc());
			pstm.setInt(6, ve.getCodCaixa());

			pstm.execute();

			System.out.println("\nVenda adicionada com sucesso.\n");

		}
	}

	public List<VendaEletro> listAll() throws SQLException {

		List<VendaEletro> listVendaEletro = new ArrayList<>();

		String sql = "SELECT v.nt_fiscal, v.cod_item, v.qtd_item, v.dt_venda, v.mat_func, v.cod_caixa, v.garant_fab as g_fab_venda, v.garant_loj as g_loj_venda FROM venda v, item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND (s.nome = 'ED' OR s.nome = 'EE')";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaEletro ve = new VendaEletro();
					ve.setNf(rs.getInt("nt_fiscal"));
					ve.setCodItem(rs.getInt("cod_item"));
					ve.setQtdItem(rs.getInt("qtd_item"));
					ve.setDtVenda(rs.getDate("dt_venda"));
					ve.setMatFunc(rs.getInt("mat_func"));
					ve.setCodCaixa(rs.getInt("cod_caixa"));
					ve.setGarantFab(rs.getDate("g_fab_venda"));
					ve.setGarantLoj(rs.getDate("g_loj_venda"));

					listVendaEletro.add(ve);

				}
			}
		}

		return listVendaEletro;
	}

	public List<VendaEletro> listByDate(Date inicio, Date fim) throws SQLException {

		List<VendaEletro> listVendaEletro = new ArrayList<>();

		String sql = "SELECT v.nt_fiscal, v.cod_item, v.qtd_item, v.dt_venda, v.mat_func, v.cod_caixa, v.garant_fab as g_fab_venda, v.garant_loj as g_loj_venda FROM venda v, item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND (s.nome = 'ED' OR s.nome = 'EE') AND dt_venda BETWEEN '"
				+ inicio + "' AND '" + fim + "'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaEletro ve = new VendaEletro();
					ve.setNf(rs.getInt("nt_fiscal"));
					ve.setCodItem(rs.getInt("cod_item"));
					ve.setQtdItem(rs.getInt("qtd_item"));
					ve.setDtVenda(rs.getDate("dt_venda"));
					ve.setMatFunc(rs.getInt("mat_func"));
					ve.setCodCaixa(rs.getInt("cod_caixa"));
					ve.setGarantFab(rs.getDate("g_fab_venda"));
					ve.setGarantLoj(rs.getDate("g_loj_venda"));

					listVendaEletro.add(ve);
				}
			}
		}

		return listVendaEletro;
	}

	public List<VendaEletro> listByMatricFunc(int matricula) throws SQLException {

		List<VendaEletro> listVendaEletro = new ArrayList<>();

		String sql = "SELECT v.nt_fiscal, v.cod_item, v.qtd_item, v.dt_venda, v.mat_func, v.cod_caixa, v.garant_fab as g_fab_venda, v.garant_loj as g_loj_venda FROM venda v, item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND (s.nome = 'ED' OR s.nome = 'EE') AND mat_func = "
				+ matricula;

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaEletro ve = new VendaEletro();
					ve.setNf(rs.getInt("nt_fiscal"));
					ve.setCodItem(rs.getInt("cod_item"));
					ve.setQtdItem(rs.getInt("qtd_item"));
					ve.setDtVenda(rs.getDate("dt_venda"));
					ve.setMatFunc(rs.getInt("mat_func"));
					ve.setCodCaixa(rs.getInt("cod_caixa"));
					ve.setGarantFab(rs.getDate("g_fab_venda"));
					ve.setGarantLoj(rs.getDate("g_loj_venda"));

					listVendaEletro.add(ve);
				}
			}
		}

		return listVendaEletro;
	}

	public List<VendaEletro> listByFuncAndDate(int matricula, Date inicio, Date fim) throws SQLException {

		List<VendaEletro> listVendaEletro = new ArrayList<>();

		String sql = "SELECT v.nt_fiscal, v.cod_item, v.qtd_item, v.dt_venda, v.mat_func, v.cod_caixa, v.garant_fab as g_fab_venda, v.garant_loj as g_loj_venda FROM venda v, item i, setor s WHERE cod_item = i.cod_barra AND i.cod_setor = s.codigo AND (s.nome = 'ED' OR s.nome = 'EE') AND mat_func = "
				+ matricula + " AND dt_venda BETWEEN '" + inicio + "' AND '" + fim + "'";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();

			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					VendaEletro ve = new VendaEletro();
					ve.setNf(rs.getInt("nt_fiscal"));
					ve.setCodItem(rs.getInt("cod_item"));
					ve.setQtdItem(rs.getInt("qtd_item"));
					ve.setDtVenda(rs.getDate("dt_venda"));
					ve.setMatFunc(rs.getInt("mat_func"));
					ve.setCodCaixa(rs.getInt("cod_caixa"));
					ve.setGarantFab(rs.getDate("g_fab_venda"));
					ve.setGarantLoj(rs.getDate("g_loj_venda"));

					listVendaEletro.add(ve);
				}
			}
		}

		return listVendaEletro;
	}

	public void update(VendaEletro ve) throws SQLException {

		String sql = "UPDATE venda SET qtd_item = ?, mat_func = ?, cod_caixa = ? WHERE nt_fiscal = ? AND cod_item = ?";

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setInt(1, ve.getQtdItem());
			pstm.setInt(2, ve.getMatFunc());
			pstm.setInt(3, ve.getCodCaixa());
			pstm.setInt(4, ve.getNf());
			pstm.setInt(5, ve.getCodItem());

			pstm.execute();

			System.out.println("\nVenda atualizada com sucesso.\n");

		}
	}

	public void updateGarantLoj(String garantia, int nf, int codItem) throws SQLException {

		String sql = "UPDATE venda SET garant_loj = garant_loj + INTERVAL '" + garantia + " year' WHERE nt_fiscal = "
				+ nf + " AND cod_item = " + codItem;

		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
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
