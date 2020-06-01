package br.cesed.si.bd2.projeto.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.cesed.si.bd2.projeto.ConnectionManager;
import br.cesed.si.bd2.projeto.dao.SetorDAO;
import br.cesed.si.bd2.projeto.models.Setor;

public class TestSetor {

	public static void main(String[] args) throws SQLException, IOException {
		dadosSetor();
	}

	private static void dadosSetor() throws SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		Setor setor = new Setor();
		List<Setor> listaSetores = new ArrayList<Setor>();

		int op = 0;

		System.out.println("*** PROJETO BD2 - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de setores ||");

		do {

			System.out.println();
			System.out.println("## Escolha uma das opções abaixo ##");
			System.out.println("# (1) - Inserir Setor #");
			System.out.println("# (2) - Listar Setores #");
			System.out.println("# (3) - Consultar Setor por nome #");
			System.out.println("# (4) - Editar Setor #");
			System.out.println("# (0) - SAIR #");

			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			if (op == 1) {

				System.out.print("Digite o código do setor: ");
				setor.setCodigo(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o nome do setor: ");
				setor.setNome(sc.nextLine());

				try (Connection conn = ConnectionManager.getConnection()) {
					SetorDAO sDao = new SetorDAO(conn);
					sDao.save(setor);

				}

			} else if (op == 2) {

				System.out.println("Lista de Setores");
				System.out.println();

				try (Connection conn = ConnectionManager.getConnection()) {
					SetorDAO sDao = new SetorDAO(conn);
					listaSetores = sDao.listAll();

					if (listaSetores.isEmpty()) {
						System.out.println("Nenhum setor cadastrado.");
						System.out.println();
					} else {

						for (Setor s : listaSetores) {
							System.out.println(s);
						}
					}

				}
			} else if (op == 3) {

				System.out.println("Lista de Setores por Nome");
				System.out.print("Digite o nome do setor: ");
				String nome = sc.nextLine();
				System.out.println();

				try (Connection conn = ConnectionManager.getConnection()) {
					SetorDAO sDao = new SetorDAO(conn);
					listaSetores = sDao.listByName(nome);

					if (listaSetores.isEmpty()) {
						System.out.println("Nenhum setor cadastrado.");
						System.out.println();
					} else {

						for (Setor s : listaSetores) {
							System.out.println(s);
						}
					}

				}
			} else if (op == 4) {

				System.out.print("Digite o numero do setor que deseja alterar: ");
				setor.setCodigo(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite um novo nome para o setor: ");
				setor.setNome(sc.nextLine());

				try (Connection conn = ConnectionManager.getConnection()) {
					SetorDAO sDao = new SetorDAO(conn);
					sDao.update(setor);

				}
			}

		} while (op != 0);

		setor = new Setor();

		sc.close();
	}

}
