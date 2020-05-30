package br.cesed.si.bd2.projeto.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.cesed.si.bd2.projeto.ConnectionManager;
import br.cesed.si.bd2.projeto.dao.RecadoDAO;
import br.cesed.si.bd2.projeto.models.Recado;

public class TestRecado {

	public static void main(String[] args) throws SQLException {
		dadosRecado();
	}

	private static void dadosRecado() throws SQLException {

		Scanner sc = new Scanner(System.in);
		Recado recado = new Recado();
		List<Recado> listRecados = new ArrayList<>();

		int op = 0;

		System.out.println("*** PROJETO BD2 - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de recado ||");

		do {

			System.out.println();
			System.out.println("## Escolha uma das opções abaixo ##");
			System.out.println("# (1) - Inserir Recado #");
			System.out.println("# (2) - Listar Recados #");
			System.out.println("# (3) - Alterar Recado #");
			System.out.println("# (4) - Apagar Recado #");
			System.out.println("# (5) - Apagar TODOS os Recados #");
			System.out.println("# (0) - SAIR #");

			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			if (op == 1) {

				System.out.print("Digite a mensagem: ");
				recado.setMensagem(sc.nextLine());

				try (Connection conn = ConnectionManager.createConnection()) {
					RecadoDAO rDao = new RecadoDAO(conn);
					rDao.save(recado);

				}

			} else if (op == 2) {

				System.out.println("Lista de Recados");
				System.out.println();

				try (Connection conn = ConnectionManager.createConnection()) {
					RecadoDAO rDao = new RecadoDAO(conn);
					listRecados = rDao.listAll();

					if (listRecados.isEmpty()) {
						System.out.println("Nenhum recado adicionado.");
						System.out.println();
					} else {

						for (Recado r : listRecados) {
							System.out.println(r);
						}
					}

				}

			} else if (op == 3) {

				System.out.print("Digite o número da Mensagem: ");
				recado.setIndice(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite a mensagem: ");
				recado.setMensagem(sc.nextLine());

				try (Connection conn = ConnectionManager.createConnection()) {
					RecadoDAO rDao = new RecadoDAO(conn);
					rDao.update(recado);

				}
			} else if (op == 4) {

				System.out.print("Digite o número da Mensagem: ");
				recado.setIndice(Integer.parseInt(sc.nextLine()));

				try (Connection conn = ConnectionManager.createConnection()) {
					RecadoDAO rDao = new RecadoDAO(conn);
					rDao.delete(recado.getIndice());

				}
			} else if (op == 5) {

				System.out.println("Tem certeza que deseja apagar todas as mensagens?");
				System.out.print("Digite S (p/ Sim) ou N (p/ Não): ");
				char opDel = sc.nextLine().charAt(0);

				if (opDel == 's' || opDel == 'S') {
					try (Connection conn = ConnectionManager.createConnection()) {
						RecadoDAO rDao = new RecadoDAO(conn);
						rDao.deleteAll();
					}
				}

			}

		} while (op != 0);

		recado = new Recado();

		sc.close();
	}
}
