package br.cesed.si.bd2.projeto.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.cesed.si.bd2.projeto.ConnectionManager;
import br.cesed.si.bd2.projeto.dao.CaixaDAO;
import br.cesed.si.bd2.projeto.enums.TipoCaixa;
import br.cesed.si.bd2.projeto.models.Caixa;

public class TestCaixa {

	public static void main(String[] args) throws SQLException, IOException {
		dadosCaixa();
	}

	private static void dadosCaixa() throws SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		Caixa caixa = new Caixa();
		List<Caixa> listCaixa = new ArrayList<>();

		int op = 0;

		System.out.println("*** PROJETO BD2 - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de caixas ||");

		do {

			System.out.println();
			System.out.println("## Escolha uma das opções abaixo ##");
			System.out.println("# (1) - Inserir Caixa #");
			System.out.println("# (2) - Listar Caixas #");
			System.out.println("# (3) - Editar Caixa #");
			System.out.println("# (4) - Excluir Caixa #");
			System.out.println("# (0) - SAIR #");

			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			if (op == 1) {

				System.out.print("Digite o número do caixa: ");
				caixa.setNumero(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o tipo do caixa [Geral/Preferencial]: ");
				String t = sc.nextLine().toUpperCase();
				TipoCaixa tipo = TipoCaixa.valueOf(t);
				caixa.setTipo(tipo);

				try (Connection conn = ConnectionManager.getConnection()) {
					CaixaDAO cDao = new CaixaDAO(conn);
					cDao.save(caixa);

				}

			} else if (op == 2) {

				System.out.println("Lista de Caixas");
				System.out.println();

				try (Connection conn = ConnectionManager.getConnection()) {
					CaixaDAO cDao = new CaixaDAO(conn);
					listCaixa = cDao.listAll();

					if (listCaixa.isEmpty()) {
						System.out.println("Nenhum caixa cadastrado.");
						System.out.println();
					} else {

						for (Caixa c : listCaixa) {
							System.out.println(c);
						}

					}

				}

			} else if (op == 3) {

				System.out.print("Digite o número do caixa que deseja alterar: ");
				caixa.setNumero(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o novo tipo do caixa [Geral/Preferencial]: ");
				String t = sc.nextLine().toUpperCase();
				TipoCaixa tipo = TipoCaixa.valueOf(t);
				caixa.setTipo(tipo);

				try (Connection conn = ConnectionManager.getConnection()) {
					CaixaDAO cDao = new CaixaDAO(conn);
					cDao.update(caixa);
				}

			} else if (op == 4) {

				System.out.print("Digite o número do caixa: ");
				int numero = Integer.parseInt(sc.nextLine());

				try (Connection conn = ConnectionManager.getConnection()) {
					CaixaDAO cDao = new CaixaDAO(conn);
					cDao.delete(numero);
				}

			}

		} while (op != 0);

		caixa = new Caixa();

		sc.close();
	}

}
