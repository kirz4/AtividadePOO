package com.example;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoJdbcDao dao = new ProdutoJdbcDao();
        Scanner scanner = new Scanner(System.in);

        try {
            ProdutoJdbcDao.criarTabela();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean running = true;
        while (running) {
            System.out.println("1. Cadastrar produto");
            System.out.println("2. Pesquisar produto");
            System.out.println("3. Alterar produto");
            System.out.println("4. Excluir produto");
            System.out.println("5. Listar produtos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar buffer do scanner

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner, dao);
                    break;
                case 2:
                    pesquisarProduto(scanner, dao);
                    break;
                case 3:
                    alterarProduto(scanner, dao);
                    break;
                case 4:
                    excluirProduto(scanner, dao);
                    break;
                case 5:
                    listarProdutos(dao);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void cadastrarProduto(Scanner scanner, ProdutoJdbcDao dao) {
        System.out.print("Tipo: ");
        String tipo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Peso (kg): ");
        double peso = scanner.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();  // Limpar buffer do scanner
        System.out.print("Unidade de medida (metro, metro quadrado, litro, kg): ");
        String unidadeMedida = scanner.nextLine();

        try {
            Produto produto = new Produto(tipo, descricao, peso, quantidade, unidadeMedida);
            dao.persistir(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void pesquisarProduto(Scanner scanner, ProdutoJdbcDao dao) {
        System.out.print("ID do produto a pesquisar: ");
        Long id = scanner.nextLong();
        Produto produto = dao.pesquisarPorId(id);
        if (produto != null) {
            System.out.println(produto);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void alterarProduto(Scanner scanner, ProdutoJdbcDao dao) {
        System.out.print("ID do produto a alterar: ");
        Long id = scanner.nextLong();
        Produto produtoExistente = dao.pesquisarPorId(id);
        if (produtoExistente != null) {
            scanner.nextLine();  // Limpar buffer do scanner
            System.out.print("Novo tipo: ");
            String tipo = scanner.nextLine();
            System.out.print("Nova descrição: ");
            String descricao = scanner.nextLine();
            System.out.print("Novo peso (kg): ");
            double peso = scanner.nextDouble();
            System.out.print("Nova quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();  // Limpar buffer do scanner
            System.out.print("Nova unidade de medida (metro, metro quadrado, litro, kg): ");
            String unidadeMedida = scanner.nextLine();

            try {
                produtoExistente.setTipo(tipo);
                produtoExistente.setDescricao(descricao);
                produtoExistente.setPeso(peso);
                produtoExistente.setQuantidade(quantidade);
                produtoExistente.setUnidadeMedida(unidadeMedida);
                dao.alterar(produtoExistente);
                System.out.println("Produto alterado com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void excluirProduto(Scanner scanner, ProdutoJdbcDao dao) {
        System.out.print("ID do produto a excluir: ");
        Long id = scanner.nextLong();
        dao.excluir(id);
        System.out.println("Produto excluído com sucesso!");
    }

    private static void listarProdutos(ProdutoJdbcDao dao) {
        List<Produto> produtos = dao.listarTodos();
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}
