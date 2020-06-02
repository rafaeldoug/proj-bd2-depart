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
import br.cesed.si.bd2.projeto.dao.VendaGeralDAO;
import br.cesed.si.bd2.projeto.models.VendaGeral;

public class TestVenda {

	public static void main(String[] args) throws SQLException, IOException {
		dadosVenda();
	}

	private static void dadosVenda() throws SQLException, IOException {

		Scanner sc = new Scanner(System.in);
		VendaGeral vg = new VendaGeral();
		List<VendaGeral> listVendaGeral = new ArrayList<>();

		int op = 0;

		System.out.println("*** PROJETO BD2 - SISTEMA LOJA DEPARTAMENTO ***");
		System.out.println("|| Controle de item ||");

		do {

			System.out.println("Selecione o tipo de Venda:");
			System.out.println("(1) - Geral");
			System.out.println("(2) - EE/ED");
			System.out.println("(0) - SAIR ");
			System.out.print("\nDigite a opção desejada: ");
			op = Integer.parseInt(sc.nextLine());

			switch (op) {

			// OPÇÕES PARA VENDAS GERAIS
			case 1:

				System.out.println();
				System.out.println("## Escolha uma das opções abaixo ##");
				System.out.println("# (1) - Inserir Venda #");
				System.out.println("# (2) - Listar Vendas #");
				System.out.println("# (3) - Listar Vendas por período #");
				System.out.println("# (4) - Listar Vendas por Funcionário #");
				System.out.println("# (5) - Alterar Venda #");
				System.out.println("# (6) - Remover Venda #");

				System.out.print("\nDigite a opção desejada: ");
				op = Integer.parseInt(sc.nextLine());

				if (op == 1) {

					System.out.print("Digite o número da NF: ");
					vg.setNf(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o cód. de barras do item: ");
					vg.setCodItem(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade do item: ");
					vg.setQtdItem(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a matrícula do vendedor: ");
					vg.setMatFunc(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o código do caixa: ");
					vg.setCodCaixa(Integer.parseInt(sc.nextLine()));

					LocalDate localDate = LocalDate.now();
					Date dateConvert = Date.valueOf(localDate);
					vg.setDtVenda(dateConvert);

					try (Connection conn = ConnectionManager.getConnection()) {
						VendaGeralDAO vgDao = new VendaGeralDAO(conn);
						vgDao.save(vg);
					}

				} else if (op == 2) {

					System.out.println("Lista de Vendas Geral");
					System.out.println();

					try (Connection conn = ConnectionManager.getConnection()) {
						VendaGeralDAO vgDao = new VendaGeralDAO(conn);
						listVendaGeral = vgDao.listAll();

						if (listVendaGeral.isEmpty()) {
							System.out.println("Nenhuma venda cadastrada.");
							System.out.println();
						} else {
							for (VendaGeral v : listVendaGeral) {
								System.out.println(v);
							}
						}
					}

				} else if (op == 3) {

					System.out.println("Lista de Vendas Geral Por Período");
					System.out.println();

					System.out.print("Digite a data de início (dd/mm/aaaa): ");
					String dmaIni = sc.nextLine();
					String dateSplitIni[] = dmaIni.split("/");
					int diaIni = (Integer.parseInt(dateSplitIni[0]));
					int mesIni = (Integer.parseInt(dateSplitIni[1]));
					int anoIni = (Integer.parseInt(dateSplitIni[2]));
					LocalDate localDateIni = LocalDate.of(anoIni, mesIni, diaIni);
					Date dtInicio = Date.valueOf(localDateIni);
					System.out.print("Digite a data de término (dd/mm/aaaa): ");
					String dmaFim = sc.nextLine();
					String dateSplitFim[] = dmaFim.split("/");
					int diaFim = (Integer.parseInt(dateSplitFim[0]));
					int mesFim = (Integer.parseInt(dateSplitFim[1]));
					int anoFim = (Integer.parseInt(dateSplitFim[2]));
					LocalDate localDateFim = LocalDate.of(anoFim, mesFim, diaFim);
					Date dtFim = Date.valueOf(localDateFim);

					System.out.println();

					try (Connection conn = ConnectionManager.getConnection()) {
						VendaGeralDAO vgDao = new VendaGeralDAO(conn);
						listVendaGeral = vgDao.listByDate(dtInicio, dtFim);

						if (listVendaGeral.isEmpty()) {
							System.out.println("Nenhuma venda cadastrada para este período.");
							System.out.println();
						} else {
							for (VendaGeral v : listVendaGeral) {
								System.out.println(v);
							}
						}

					}

					System.out.println("- fim -");
				} else if (op == 4) {

					System.out.println("(1) - Busca por matrícula");
					System.out.println("(2) - Busca por nome");
					System.out.print("\nDigite a opção desejada: ");
					op = Integer.parseInt(sc.nextLine());

					switch (op) {
					case 1:

						System.out.print("Digite a matrícula do vendedor: ");
						int matricula = Integer.parseInt(sc.nextLine());

						try (Connection conn = ConnectionManager.getConnection()) {
							VendaGeralDAO vgDao = new VendaGeralDAO(conn);
							listVendaGeral = vgDao.listByMatricFunc(matricula);

							if (listVendaGeral.isEmpty()) {
								System.out.println("Nenhuma venda cadastrada para esta matrícula.");
								System.out.println();
							} else {
								for (VendaGeral v : listVendaGeral) {
									System.out.println(v);
								}
							}

						}
						System.out.println("- fim -");

						break;
					case 2:
						System.out.print("Digite a nome do vendedor: ");
						String nome = sc.nextLine();

						try (Connection conn = ConnectionManager.getConnection()) {
							VendaGeralDAO vgDao = new VendaGeralDAO(conn);
							listVendaGeral = vgDao.listByNomeFunc(nome);

							if (listVendaGeral.isEmpty()) {
								System.out.println("Nenhuma venda cadastrada para este nome.");
								System.out.println();
							} else {
								for (VendaGeral v : listVendaGeral) {
									System.out.println(v);
								}
							}

						}
						System.out.println("- fim -");

						break;
					default:
						break;
					}

				} else if (op == 5) {

					System.out.print("Digite o número da NF: ");
					vg.setNf(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o cód. de barras do item: ");
					vg.setCodItem(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a quantidade do item: ");
					vg.setQtdItem(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite a matrícula do vendedor: ");
					vg.setMatFunc(Integer.parseInt(sc.nextLine()));
					System.out.print("Digite o código do caixa: ");
					vg.setCodCaixa(Integer.parseInt(sc.nextLine()));

					try (Connection conn = ConnectionManager.getConnection()) {
						VendaGeralDAO vgDao = new VendaGeralDAO(conn);
						vgDao.update(vg);
					}

				} else if (op == 6) {

					System.out.print("Digite o número da NF: ");
					int nf = Integer.parseInt(sc.nextLine());
					System.out.print("Digite o cód. de barras do item: ");
					int codItem = Integer.parseInt(sc.nextLine());

					try (Connection conn = ConnectionManager.getConnection()) {
						VendaGeralDAO vgDao = new VendaGeralDAO(conn);
						vgDao.delete(nf, codItem);
					}

				}

				break;

			case 2:

				break;

			default:
				break;

			}
		} while (op != 0);

		vg = new VendaGeral();
		listVendaGeral.clear();

		sc.close();
	}
}
