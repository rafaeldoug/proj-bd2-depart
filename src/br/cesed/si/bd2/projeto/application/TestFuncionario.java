package br.cesed.si.bd2.projeto.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.cesed.si.bd2.projeto.ConnectionManager;
import br.cesed.si.bd2.projeto.dao.FuncionarioDAO;
import br.cesed.si.bd2.projeto.models.Funcionario;

public class TestFuncionario {

	public static void main(String[] args) throws SQLException, IOException {
		dadosFuncionarios();

	}

	public static void dadosFuncionarios() throws SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		Funcionario func = new Funcionario();
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		int op = 0;

		System.out.println("*** PROJETO BD2- SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de funcionários ||");

		do {

			System.out.println();
			System.out.println("## Escolha uma das opções abaixo ##");
			System.out.println("# (1) - Inserir Funcionário #");
			System.out.println("# (2) - Listar Funcionários #");
			System.out.println("# (3) - Buscar Funcionário por Nome #");
			System.out.println("# (4) - Buscar Funcionário por Função #");
			System.out.println("# (5) - Classificar Funcionários #");
			System.out.println("# (6) - Alterar salário #");
			System.out.println("# (7) - Alterar dados da Demissão #");
			System.out.println("# (0) - SAIR #");

			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			if (op == 1) {

				System.out.print("Digite a matrícula do funcionário: ");
				func.setMatricula(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o nome do funcionário: ");
				func.setNome(sc.nextLine());
				System.out.print("Digite o CPF do funcionário: ");
				func.setCpf(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o salário do funcionário: ");
				func.setSalario(Double.parseDouble(sc.nextLine()));
				System.out.print("Digite a função do funcionário: ");
				func.setFuncao(sc.nextLine());
				System.out.print("Digite a data de Admissão (dd/mm/aaaa): ");
				String dma = sc.nextLine();
				String dateSplit[] = dma.split("/");
				int dia = (Integer.parseInt(dateSplit[0]));
				int mes = (Integer.parseInt(dateSplit[1]));
				int ano = (Integer.parseInt(dateSplit[2]));
				LocalDate localDate = LocalDate.of(ano, mes, dia);
				Date dateConvert = Date.valueOf(localDate);
				func.setDtAdmissao(dateConvert);

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.save(func);
				}

			} else if (op == 2) {

				System.out.println("Lista de Funcionários");
				System.out.println();

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDao = new FuncionarioDAO(conn);
					listaFuncionarios = funcDao.listAll();

					if (listaFuncionarios.isEmpty()) {
						System.out.println("Nenhum funcionário cadastrado.");
						System.out.println();
					} else {

						for (Funcionario f : listaFuncionarios) {
							System.out.println(f);
						}
					}

				}
			} else if (op == 3) {

				System.out.print("Digite o nome do funcionário que deseja buscar: ");
				String nome = sc.nextLine();
				System.out.println();

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDao = new FuncionarioDAO(conn);
					listaFuncionarios = funcDao.listByName(nome);

					if (listaFuncionarios.isEmpty()) {
						System.out.println("Nenhum funcionário com este nome cadastrado.");
						System.out.println();
					} else {

						for (Funcionario f : listaFuncionarios) {
							System.out.println(f);
						}
					}

				}
			} else if (op == 4) {

				System.out.print("Digite a função do funcionário que deseja buscar: ");
				String funcao = sc.nextLine();
				System.out.println();

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDao = new FuncionarioDAO(conn);
					listaFuncionarios = funcDao.listByFuncao(funcao);

					if (listaFuncionarios.isEmpty()) {
						System.out.println("Nenhum funcionário com esta função cadastrado.");
						System.out.println();
					} else {

						for (Funcionario f : listaFuncionarios) {
							System.out.println(f);
						}
					}

				}

			} else if (op == 5) {

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.classificaFuncionario();
				}

			} else if (op == 6) {

				System.out.print("Digite o CPF do funcionário: ");
				func.setCpf(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o salário do funcionário: ");
				func.setSalario(Double.parseDouble(sc.nextLine()));

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.updateSalario(func);
				}

			} else if (op == 7) {

				System.out.print("Digite o CPF do funcionário: ");
				func.setCpf(Integer.parseInt(sc.nextLine()));
				System.out.println();
				System.out.println("- Alterar data de demissão -");
				System.out.print("Digite a data (dd/mm/aaaa): ");
				String dma = sc.nextLine();
				System.out.println(dma);
				String dateSplit[] = dma.split("/");
				int dia = (Integer.parseInt(dateSplit[0]));
				int mes = (Integer.parseInt(dateSplit[1]));
				int ano = (Integer.parseInt(dateSplit[2]));
				LocalDate localDate = LocalDate.of(ano, mes, dia);
				Date dateConvert = Date.valueOf(localDate);
				func.setDtDemissao(dateConvert);
				System.out.print("Digite o motivo da demissão: ");
				func.setMotivo(sc.nextLine());

				try (Connection conn = ConnectionManager.getConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.saveDemissao(func);
				}

			}

			func = new Funcionario();

		} while (op != 0);

		sc.close();
	}
}
