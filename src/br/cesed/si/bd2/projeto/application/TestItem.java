package br.cesed.si.bd2.projeto.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.cesed.si.bd2.projeto.ConnectionManager;
import br.cesed.si.bd2.projeto.dao.ItemGeralDAO;
import br.cesed.si.bd2.projeto.models.Item;

public class TestItem {

	public static void main(String[] args) throws SQLException {
		dadosItem();
	}

	private static void dadosItem() throws SQLException {

		Scanner sc = new Scanner(System.in);
		Item item = new Item();
		List<Item> listItem = new ArrayList<>();

		int op = 0;

		System.out.println("*** PROJETO BD2 - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de item ||");

		do {

			System.out.println();
			System.out.println("## Escolha uma das opções abaixo ##");
			System.out.println("# (1) - Inserir Item #");
			System.out.println("# (2) - Listar Itens #");
			System.out.println("# (3) - Listar Itens por Descrição #");
			System.out.println("# (4) - Listar Itens sem Estoque #");
			System.out.println("# (0) - SAIR #");

			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			if (op == 1) {

				System.out.println("Seleciona o tipo de Item:");
				System.out.println("(1) - Geral");
				System.out.println("(2) - EE/ED");
				System.out.print("\nDigite a opção desejada: ");
				op = Integer.parseInt(sc.nextLine());

				switch (op) {
				case 1:
					System.out.print("Digite o nome do Item: ");
					item.setNome(sc.nextLine());
					System.out.print("Digite o código de barras: ");
					item.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o preço: ");
					item.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					item.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade (digite 0 para nenhum): ");
					item.setQuantidade(Integer.parseInt(sc.nextLine()));

					break;

				case 2:
					System.out.print("Digite o nome do Item: ");
					item.setNome(sc.nextLine());
					System.out.print("Digite o código de barras: ");
					item.setCodBarra(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o preço: ");
					item.setPreco(Double.parseDouble(sc.nextLine()));
					System.out.print("Digite o número do Setor: ");
					item.setCodSetor(Integer.parseInt(sc.nextLine()));
					System.out.print("Garantia da Loja (em anos): ");
					item.setGarantiaLoja(Integer.parseInt(sc.nextLine()));
					System.out.print("Garantia de Fábrica (em anos): ");
					item.setGarantiaFabricante(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade (digite 0 para nenhum): ");
					item.setQuantidade(Integer.parseInt(sc.nextLine()));

					break;

				default:
					break;
				}

				try (Connection conn = ConnectionManager.createConnection()) {
					ItemGeralDAO igDao = new ItemGeralDAO(conn);
					igDao.save(item);

				}

			} else if (op == 2) {

				System.out.println("Lista de Itens");
				System.out.println();

				try (Connection conn = ConnectionManager.createConnection()) {
					ItemGeralDAO igDao = new ItemGeralDAO(conn);
					listItem = igDao.listAll();

					if (listItem.isEmpty()) {
						System.out.println("Nenhum item cadastrado.");
						System.out.println();
					} else {

						for (Item i : listItem) {
							System.out.println(i);
						}

					}

				}
				
				System.out.println("- fim -");

			} else if (op == 3) {
				
				System.out.println("Lista de Itens por Descrição");
				System.out.println();
				
				System.out.print("Digite a nome do produto: ");
				String descricao = sc.nextLine();
				
				try (Connection conn = ConnectionManager.createConnection()) {
					ItemGeralDAO igDao = new ItemGeralDAO(conn);
					listItem = igDao.listByDescricao(descricao);
					
					if (listItem.isEmpty()) {
						System.out.println("Nenhum item cadastrado.");
						System.out.println();
					} else {
						
						for (Item i : listItem) {
							System.out.println(i);
						}
						
					}
				}
				
				System.out.println("- fim -");
				
			} else if (op == 4) {
				
				System.out.println("Lista de Itens sem Estoque");
				System.out.println();
				
				try (Connection conn = ConnectionManager.createConnection()) {
					ItemGeralDAO igDao = new ItemGeralDAO(conn);
					listItem = igDao.listByEstoque();
					
					if (listItem.isEmpty()) {
						System.out.println("Nenhum item sem estoque.");
						System.out.println();
					} else {
						
						for (Item i : listItem) {
							System.out.println(i);
						}
						
					}		
				}
				
				System.out.println("- fim -");
				
			} else if (op == 10) {

//				System.out.print("Digite o número do caixa que deseja alterar: ");
//				caixa.setNumero(Integer.parseInt(sc.nextLine()));
//				System.out.print("Digite o novo tipo do caixa [Geral/Preferencial]: ");
//				String t = sc.nextLine().toUpperCase();
//				TipoCaixa tipo = TipoCaixa.valueOf(t);
//				caixa.setTipo(tipo);
//
//				try (Connection conn = ConnectionManager.createConnection()) {
//					CaixaDAO cDao = new CaixaDAO(conn);
//					cDao.updateCaixa(caixa);
//				}

			} else if (op == 4) {

//				System.out.print("Digite o número do caixa: ");
//				int numero = Integer.parseInt(sc.nextLine());
//
//				try (Connection conn = ConnectionManager.createConnection()) {
//					CaixaDAO cDao = new CaixaDAO(conn);
//					cDao.delete(numero);
//				}

			}

		} while (op != 0);

		item = new Item();

		sc.close();
	}

}
