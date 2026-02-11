package cadastropoo;

import model.*;
import java.util.Scanner;

public class CadastroPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Exibir pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    char tipo = scanner.next().toUpperCase().charAt(0);
                    if (tipo == 'F') {
                        System.out.print("ID: "); int id = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("CPF: "); String cpf = scanner.nextLine();
                        System.out.print("Idade: "); int idade = scanner.nextInt();
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo == 'J') {
                        System.out.print("ID: "); int id = scanner.nextInt();
                        scanner.nextLine(); 
                        System.out.print("Nome: "); String nome = scanner.nextLine();
                        System.out.print("CNPJ: "); String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                    break;

                case 2:
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    char tipoAlt = scanner.next().toUpperCase().charAt(0);
                    if (tipoAlt == 'F') {
                        System.out.print("ID da Pessoa a alterar: "); int id = scanner.nextInt();
                        PessoaFisica p = repoFisica.obter(id);
                        if (p != null) {
                            System.out.println("Dados atuais:"); p.exibir();
                            scanner.nextLine();
                            System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                            System.out.print("Novo CPF: "); String cpf = scanner.nextLine();
                            System.out.print("Nova Idade: "); int idade = scanner.nextInt();
                            repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                        } else System.out.println("Pessoa não encontrada.");
                    } else if (tipoAlt == 'J') {
                        System.out.print("ID da Pessoa a alterar: "); int id = scanner.nextInt();
                        PessoaJuridica p = repoJuridica.obter(id);
                        if (p != null) {
                            System.out.println("Dados atuais:"); p.exibir();
                            scanner.nextLine();
                            System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                            System.out.print("Novo CNPJ: "); String cnpj = scanner.nextLine();
                            repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        } else System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    char tipoExc = scanner.next().toUpperCase().charAt(0);
                    System.out.print("ID a excluir: "); int idExc = scanner.nextInt();
                    if (tipoExc == 'F') repoFisica.excluir(idExc);
                    else if (tipoExc == 'J') repoJuridica.excluir(idExc);
                    break;

                case 4:
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    char tipoExib = scanner.next().toUpperCase().charAt(0);
                    System.out.print("ID: "); int idExib = scanner.nextInt();
                    if (tipoExib == 'F') {
                        PessoaFisica pf = repoFisica.obter(idExib);
                        if (pf != null) pf.exibir(); else System.out.println("Não encontrado.");
                    } else if (tipoExib == 'J') {
                        PessoaJuridica pj = repoJuridica.obter(idExib);
                        if (pj != null) pj.exibir(); else System.out.println("Não encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("F - Pessoa Fisica | J - Pessoa Juridica: ");
                    char tipoTodos = scanner.next().toUpperCase().charAt(0);
                    if (tipoTodos == 'F') {
                        for (PessoaFisica pf : repoFisica.obterTodos()) { pf.exibir(); System.out.println("---"); }
                    } else if (tipoTodos == 'J') {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) { pj.exibir(); System.out.println("---"); }
                    }
                    break;

                case 6:
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoSalvar = scanner.next();
                    try {
                        repoFisica.persistir(prefixoSalvar + ".fisica.bin");
                        repoJuridica.persistir(prefixoSalvar + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao salvar: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.print("Digite o prefixo dos arquivos: ");
                    String prefixoRec = scanner.next();
                    try {
                        repoFisica.recuperar(prefixoRec + ".fisica.bin");
                        repoJuridica.recuperar(prefixoRec + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar: " + e.getMessage());
                    }
                    break;
                    
                case 0: 
                    System.out.println("Encerrando...");
                    break;
            }
        }
        scanner.close();
    }
}