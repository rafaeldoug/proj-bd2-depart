package br.cesed.si.bd2.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
