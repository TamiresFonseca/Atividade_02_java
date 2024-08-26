package com.lanchenlayer;

import com.lanchenlayer.applications.ProdutoApplication;
import com.lanchenlayer.entities.Produto;
import com.lanchenlayer.facade.ProdutoFacade;
import com.lanchenlayer.repositories.ProdutoRepository;
import com.lanchenlayer.services.ProdutoService;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Hello world!
 *
 */

        public class Console {
            public static void main(String[] args) {
                ProdutoRepository produtoRepository = new ProdutoRepository();
                ProdutoService produtoService = new ProdutoService();
                ProdutoApplication produtoApplication = new ProdutoApplication(produtoRepository, produtoService);
                ProdutoFacade produtoFacade = new ProdutoFacade(produtoApplication);

                produtoFacade.adicionar(new Produto(1, "Cachorro quente", 4.00f, "C:\\Users\\aluno\\imagens\\cachorroquente.jpg"));
                produtoFacade.adicionar(new Produto(2, "X-Salada", 5.00f, "C:\\Users\\aluno\\imagens\\xsalada.jpg"));

                menu(produtoFacade);
            }

            public static void menu(ProdutoFacade produtoFacade) {
                Scanner scanner = new Scanner(System.in);
                int opcao;

                do {
                    System.out.println("Menu:");
                    System.out.println("1. Adicionar Produto");
                    System.out.println("2. Remover Produto");
                    System.out.println("3. Buscar Produto por ID");
                    System.out.println("4. Listar Todos os Produtos");
                    System.out.println("5. Vender Produto");
                    System.out.println("0. Sair");
                    System.out.print("Escolha uma opção: ");
                    opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            System.out.print("ID do Produto: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Descrição do Produto: ");
                            String descricao = scanner.nextLine();
                            System.out.print("Preço do Produto: ");
                            float preco = scanner.nextFloat();
                            scanner.nextLine();
                            System.out.print("Adicionar Imagem: ");
                            String imagem = scanner.nextLine();

                            Produto novoProduto = new Produto(id, descricao, preco, imagem);
                            produtoFacade.adicionar(novoProduto);
                            break;

                        case 2:
                            System.out.print("Informe o ID do Produto que vai ser removido: ");
                            int idRemover = scanner.nextInt();
                            produtoFacade.remover(idRemover);
                            break;

                        case 3:
                            System.out.print("ID do Produto: ");
                            int idBuscar = scanner.nextInt();
                            Produto produto = produtoFacade.buscarPorId(idBuscar);
                            if (produto != null) {
                                System.out.println("Produto encontrado: " + produto.getDescricao());
                            } else {
                                System.out.println("Produto não encontrado.");
                            }


                        case 4:
                            ArrayList<Produto> produtos = produtoFacade.buscarTodos();
                            produtos.forEach(p -> {
                                System.out.println(p.getId() + " - " + p.getDescricao() + " - R$ " + p.getValor());
                            });
                            break;

                        case 5:
                            System.out.print("ID do Produto a vender: ");
                            int idVender = scanner.nextInt();
                            produtoFacade.vender(idVender);
                            break;

                        case 0:
                            System.out.println("Saindo...");
                            break;

                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            break;
                    }
                } while (opcao != 0);
            }
        }

