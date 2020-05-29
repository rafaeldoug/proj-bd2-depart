package br.cesed.si.bd2.projeto.application;

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

	public static void main(String[] args) throws SQLException {
		dadosFuncionarios();

	}

	public static void dadosFuncionarios() throws SQLException {

		Scanner sc = new Scanner(System.in);
		Funcionario func = new Funcionario();
		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		int op = 0;

		System.out.println("*** PROJETO - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Cadastro de funcion�rios ||");

		do {

			System.out.println("## Escolha uma das op��es abaixo ##");
			System.out.println("# (1) - Inserir Funcion�rio #");
			System.out.println("# (2) - Listar Funcion�rios #");
			System.out.println("# (3) - Alterar sal�rio #");
			System.out.println("# (4) - Alterar dados da Demiss�o #");
			System.out.println("# (0) - SAIR #");

			System.out.print("\nDigite a op��o desejada: ");
			op = Integer.parseInt(sc.nextLine());

			if (op == 1) {

				System.out.print("Digite a matr�cula do funcion�rio: ");
				func.setMatricula(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o nome do funcion�rio: ");
				func.setNome(sc.nextLine());
				System.out.print("Digite o CPF do funcion�rio: ");
				func.setCpf(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o sal�rio do funcion�rio: ");
				func.setSalario(Double.parseDouble(sc.nextLine()));
				System.out.print("Digite a fun��o do funcion�rio: ");
				func.setFuncao(sc.nextLine());

				try (Connection conn = ConnectionManager.createConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.save(func);
				}

			} else if (op == 2) {

				System.out.println("Lista de Funcion�rios");
				System.out.println();

				try (Connection conn = ConnectionManager.createConnection()) {
					FuncionarioDAO funcDao = new FuncionarioDAO(conn);
					listaFuncionarios = funcDao.listAll();

					if (listaFuncionarios.isEmpty()) {
						System.out.println("Nenhum funcion�rio cadastrado.");
						System.out.println();
					} else {

						for (Funcionario f : listaFuncionarios) {
							System.out.println(f);
						}
					}

				}

			} else if (op == 3) {

				System.out.print("Digite o CPF do funcion�rio: ");
				func.setCpf(Integer.parseInt(sc.nextLine()));
				System.out.print("Digite o sal�rio do funcion�rio: ");
				func.setSalario(Double.parseDouble(sc.nextLine()));

				try (Connection conn = ConnectionManager.createConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.updateSalario(func);
				}

			} else if (op == 4) {

				System.out.print("Digite o CPF do funcion�rio: ");
				func.setCpf(Integer.parseInt(sc.nextLine()));
				System.out.println();
				System.out.println("- Alterar data de demiss�o -");
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
				System.out.print("Digite o motivo da demiss�o: ");
				func.setMotivo(sc.nextLine());

				try (Connection conn = ConnectionManager.createConnection()) {
					FuncionarioDAO funcDAO = new FuncionarioDAO(conn);
					funcDAO.saveDemissao(func);
				}

			}

			func = new Funcionario();

		} while (op != 0);

		sc.close();
	}
}
