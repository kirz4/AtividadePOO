package com.example;

public class Main {
    public static void main(String[] args) {
        // Criação de uma venda para teste
        Venda venda = new Venda("Cliente Exemplo");
        
        // Adicionando itens à venda
        venda.vender("Produto A", 100.0, 2);
        venda.vender("Produto B", 50.0, 3);

        // Exibindo informações da venda
        System.out.println("Cliente: " + venda.getCliente());
        System.out.println("Situação: " + venda.getSituacao());
        System.out.println("Valor Total: " + venda.getValor());

        // Aplicando desconto
        boolean descontoAplicado = venda.aplicarDesconto(10);
        System.out.println("Desconto aplicado: " + descontoAplicado);
        System.out.println("Valor Total após desconto: " + venda.getValor());

        // Finalizando a venda
        venda.finalizar();
        System.out.println("Situação após finalizar: " + venda.getSituacao());
    }
}
