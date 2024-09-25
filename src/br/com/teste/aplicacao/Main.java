package br.com.teste.aplicacao;

import java.util.Date;
import java.util.Scanner;

import br.com.teste.dao.ContatosDAO;
import br.com.teste.model.Contatos;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean exit = false;
		
		ContatosDAO contatosDAO = new ContatosDAO();
		Contatos contatos = new Contatos();
		
		do {
			try {
				System.out.println("1. Registrar informação do contato");
				System.out.println("2. Ver todas as informações dos contatos");
				System.out.println("3. Editar informação do contato baseado no ID");
				System.out.println("4. Deletar informação do contato baseado no ID");
				System.out.println("8. Sair");
				
				int option = sc.nextInt();
				
				switch(option) {
					case 1:
						System.out.println("Criar quantos contatos?");
						try {
							int qNC = sc.nextInt();
							sc.nextLine();
							for(int i=0;i<qNC;i++) {
								System.out.println("Nome do contato");
								contatos.setNome(sc.nextLine());
								boolean validInput = false;
								while(!validInput) {
									try {
										System.out.println("Idade do contato");
										contatos.setIdade(sc.nextInt());
										validInput = true;
										sc.nextLine();
									}catch(java.util.InputMismatchException e) {
										System.out.println("Idade invalida \n");
										sc.nextLine();
									}
								}
								contatos.setDataCadastro(new Date());
								contatosDAO.save(contatos);
							}
						}catch(java.util.InputMismatchException e) {
							System.out.println("Entrada invalida \n");
							sc.nextLine();
						}
						break;
					case 2:
						for(Contatos c : contatosDAO.getContatos()) {
							System.out.printf("Id: %s%n| Nome: %s | Idade: %s%n| DataCadastro: %s\n\n",c.getId(), c.getNome(), c.getIdade(), c.getDataCadastro());
						}
						break;
					case 3:
						System.out.println("Editar quantos contatos?");
						try {
							int qNE = sc.nextInt();
							sc.nextLine();
							for(int i=0;i<qNE;i++) {
								boolean validInput = false;
								while(!validInput) {
									try {
										System.out.println("ID do contato");
										contatos.setId(sc.nextInt());
										validInput = true;
										sc.nextLine();
									}catch(java.util.InputMismatchException e) {
										System.out.println("ID invalido \n");
										sc.nextLine();
									}
								}
								System.out.println("Novo nome do contato");
								contatos.setNome(sc.nextLine());
								validInput = false;
								while(!validInput) {
									try {
										System.out.println("Nova idade do contato");
										contatos.setIdade(sc.nextInt());
										validInput = true;
										sc.nextLine();
									}catch(java.util.InputMismatchException e) {
										System.out.println("Idade invalida \n");
										sc.nextLine();
									}
								}
								contatos.setDataCadastro(new Date());
								contatosDAO.update(contatos);
							}
						}catch(java.util.InputMismatchException e) {
							System.out.println("Entrada invalida \n");
							sc.nextLine();
						}
						break;
					case 4:
						System.out.println("Deletar quantos contatos?");
						try {
							int qND = sc.nextInt();
							for(int i=0;i<qND;i++) {
								boolean validInput = false;
								while(!validInput) {
									try {
										System.out.println("ID do contato");
										contatosDAO.deleteByID(sc.nextInt());
										validInput = true;
									}catch(java.util.InputMismatchException e) {
										System.out.println("ID invalido \n");
										sc.nextLine();
									}
								}
							}
						}catch(java.util.InputMismatchException e) {
							System.out.println("Entrada invalida \n");
							sc.nextLine();
						}
						break;
					case 8:
						exit = true;
						System.out.println("Até mais");
						break;
					default:
						break;
				}
			}catch(java.util.InputMismatchException e) {
				System.out.println("Entrada invalida \n");
				sc.nextLine();
			}
		}while(!exit);
	}
}
