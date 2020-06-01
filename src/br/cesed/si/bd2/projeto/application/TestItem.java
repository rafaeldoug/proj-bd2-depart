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
import br.cesed.si.bd2.projeto.dao.ItemGeralDAO;
import br.cesed.si.bd2.projeto.models.ItemEletro;
import br.cesed.si.bd2.projeto.models.ItemGeral;

public class TestItem {

	public static void main(String[] args) throws SQLException, IOException {
		dadosItem();
	}

	private static void dadosItem() throws SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		ItemGeral itemG = new ItemGeral();
		ItemEletro itemE = new ItemEletro();
		List<ItemGeral> listGeral = new ArrayList<>();
		List<ItemEletro> listEletro = new ArrayList<>();

		int op = 0;

		System.out.println("*** PROJETO BD2 - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de item ||");

		do {

			System.out.println("Seleciona o tipo de Item:");
			System.out.println("(1) - Geral");
			System.out.println("(2) - EE/ED");
			System.out.println("(0) - SAIR ");
			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			switch (op) {
			case 1:

				System.out.println();
				System.out.println("## Escolha uma das opções abaixo ##");
				System.out.println("# (1) - Inserir Item #");
				System.out.println("# (2) - Inserir Item (sem prazo de validade) #");
				System.out.println("# (3) - Listar Itens #");
				System.out.println("# (4) - Listar Itens por Descrição #");
				System.out.println("# (5) - Listar Itens sem Estoque #");
				System.out.println("# (6) - Alterar Item #");
				System.out.println("# (7) - Alterar Item (com validade) #");
				System.out.println("# (8) - Alterar Estoque do Item #");

				System.out.print("\nDigite a opção desejada: ");
				op = Integer.parseInt(sc.nextLine());

				if (op == 1) {

					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o código de barras: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a validade (dd/mm/aaaa): ");
					String dma = sc.nextLine();
					String dateSplit[] = dma.split("/");
					int dia = (Integer.parseInt(dateSplit[0]));
					int mes = (Integer.parseInt(dateSplit[1]));
					int ano = (Integer.parseInt(dateSplit[2]));
					LocalDate localDate = LocalDate.of(ano, mes, dia);
					Date dateConvert = Date.valueOf(localDate);
					itemG.setValidade(dateConvert);
					System.out.print("Digite a quantidade (digite 0 para nenhum): ");
					itemG.setQuantidade(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.save(itemG);

					}

				} else if (op == 2) {

					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o código de barras: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade (digite 0 para nenhum): ");
					itemG.setQuantidade(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.save(itemG);

					}

				} else if (op == 3) {

					System.out.println("Lista de Itens");
					System.out.println();

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						listGeral = igDao.listAll();

						if (listGeral.isEmpty()) {
							System.out.println("Nenhum item cadastrado.");
							System.out.println();
						} else {

							for (ItemGeral i : listGeral) {
								System.out.println(i);
							}

						}

					}

					System.out.println("- fim -");

				} else if (op == 4) {

					System.out.println("Lista de Itens por Descrição");
					System.out.println();

					System.out.print("Digite a nome do produto: ");
					String descricao = sc.nextLine();

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						listGeral = igDao.listByDescricao(descricao);

						if (listGeral.isEmpty()) {
							System.out.println("Nenhum item cadastrado.");
							System.out.println();
						} else {

							for (ItemGeral i : listGeral) {
								System.out.println(i);
							}

						}
					}

					System.out.println("- fim -");

				} else if (op == 5) {

					System.out.println("Lista de Itens sem Estoque");
					System.out.println();

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						listGeral = igDao.listByEstoque();

						if (listGeral.isEmpty()) {
							System.out.println("Nenhum item sem estoque.");
							System.out.println();
						} else {

							for (ItemGeral i : listGeral) {
								System.out.println(i);
							}

						}
					}

					System.out.println("- fim -");

				} else if (op == 6) {

					System.out.print("Digite o código de barras do item que deseja alterar: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.update(itemG);

					}
				} else if (op == 7) {

					System.out.print("Digite o código de barras do item que deseja alterar: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a validade (dd/mm/aaaa): ");
					String dma = sc.nextLine();
					String dateSplit[] = dma.split("/");
					int dia = (Integer.parseInt(dateSplit[0]));
					int mes = (Integer.parseInt(dateSplit[1]));
					int ano = (Integer.parseInt(dateSplit[2]));
					LocalDate localDate = LocalDate.of(ano, mes, dia);
					Date dateConvert = Date.valueOf(localDate);
					itemG.setValidade(dateConvert);

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.updateComValidade(itemG);

					}

				} else if (op == 8) {

					System.out.print("Digite o código de barras do item que deseja alterar: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade do Item: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.updateQuantidade(itemG);
					}

				}

				break;

			case 2:

				System.out.println();
				System.out.println("## Escolha uma das opções abaixo ##");
				System.out.println("# (1) - Inserir Item #");
				System.out.println("# (2) - Listar Itens #");
				System.out.println("# (3) - Listar Itens por Descrição #");
				System.out.println("# (4) - Listar Itens sem Estoque #");
				System.out.println("# (5) - Alterar Item (com validade) #");
				System.out.println("# (6) - Alterar Estoque do Item #");

				System.out.print("\nDigite a opção desejada: ");
				op = Integer.parseInt(sc.nextLine());

				if (op == 1) {

					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o código de barras: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a validade (dd/mm/aaaa): ");
					String dma = sc.nextLine();
					String dateSplit[] = dma.split("/");
					int dia = (Integer.parseInt(dateSplit[0]));
					int mes = (Integer.parseInt(dateSplit[1]));
					int ano = (Integer.parseInt(dateSplit[2]));
					LocalDate localDate = LocalDate.of(ano, mes, dia);
					Date dateConvert = Date.valueOf(localDate);
					itemG.setValidade(dateConvert);
					System.out.print("Digite a quantidade (digite 0 para nenhum): ");
					itemG.setQuantidade(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.save(itemG);

					}

				} else if (op == 2) {

					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o código de barras: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade (digite 0 para nenhum): ");
					itemG.setQuantidade(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.save(itemG);

					}

				} else if (op == 3) {

					System.out.println("Lista de Itens");
					System.out.println();

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						listGeral = igDao.listAll();

						if (listGeral.isEmpty()) {
							System.out.println("Nenhum item cadastrado.");
							System.out.println();
						} else {

							for (ItemGeral i : listGeral) {
								System.out.println(i);
							}

						}

					}

					System.out.println("- fim -");

				} else if (op == 4) {

					System.out.println("Lista de Itens por Descrição");
					System.out.println();

					System.out.print("Digite a nome do produto: ");
					String descricao = sc.nextLine();

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						listGeral = igDao.listByDescricao(descricao);

						if (listGeral.isEmpty()) {
							System.out.println("Nenhum item cadastrado.");
							System.out.println();
						} else {

							for (ItemGeral i : listGeral) {
								System.out.println(i);
							}

						}
					}

					System.out.println("- fim -");

				} else if (op == 5) {

					System.out.println("Lista de Itens sem Estoque");
					System.out.println();

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						listGeral = igDao.listByEstoque();

						if (listGeral.isEmpty()) {
							System.out.println("Nenhum item sem estoque.");
							System.out.println();
						} else {

							for (ItemGeral i : listGeral) {
								System.out.println(i);
							}

						}
					}

					System.out.println("- fim -");

				} else if (op == 6) {

					System.out.print("Digite o código de barras do item que deseja alterar: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.update(itemG);

					}
				} else if (op == 7) {

					System.out.print("Digite o código de barras do item que deseja alterar: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o nome do Item: ");
					itemG.setNome(sc.nextLine());
					System.out.print("Digite o preço: ");
					itemG.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a validade (dd/mm/aaaa): ");
					String dma = sc.nextLine();
					String dateSplit[] = dma.split("/");
					int dia = (Integer.parseInt(dateSplit[0]));
					int mes = (Integer.parseInt(dateSplit[1]));
					int ano = (Integer.parseInt(dateSplit[2]));
					LocalDate localDate = LocalDate.of(ano, mes, dia);
					Date dateConvert = Date.valueOf(localDate);
					itemG.setValidade(dateConvert);

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.updateComValidade(itemG);

					}

				} else if (op == 8) {

					System.out.print("Digite o código de barras do item que deseja alterar: ");
					itemG.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade do Item: ");
					itemG.setCodSetor(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						ItemGeralDAO igDao = new ItemGeralDAO(conn);
						igDao.updateQuantidade(itemG);
					}

				}

				break;

			default:
				break;
			}

		} while (op != 0);

		itemG = new ItemGeral();

		sc.close();
	}

}
