package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item item;

    @BeforeEach
    public void setUp() {
        item = new Item("Produto Teste", 2.0, 10.0);
    }

    @Test
    public void testProdutoInicializadoCorretamente() {
        System.out.println("Teste de inicialização do produto...");
        assertEquals("Produto Teste", item.getProduto(), "Verificando se o produto foi inicializado corretamente");
    }

    @Test
    public void testQuantidadeInicializadaCorretamente() {
        System.out.println("Teste de inicialização da quantidade...");
        assertEquals(2.0, item.getQuantidade(), "Verificando se a quantidade foi inicializada corretamente");
    }

    @Test
    public void testPrecoUnitarioInicializadoCorretamente() {
        System.out.println("Teste de inicialização do preço unitário...");
        assertEquals(10.0, item.getPrecoUnitario(), "Verificando se o preço unitário foi inicializado corretamente");
    }

    @Test
    public void testValorUnitarioCorreto() {
        System.out.println("Teste de cálculo do valor unitário...");
        assertEquals(20.0, item.getValorUnitario(), "Verificando se o valor unitário (preço * quantidade) está correto");
    }

    @Test
    public void testAplicarDescontoValido() {
        System.out.println("Teste de aplicação de desconto válido...");
        assertTrue(item.aplicarDesconto(50), "Verificando se o desconto de 50% foi aplicado corretamente");
        assertEquals(5.0, item.getPrecoUnitario(), "Verificando se o preço unitário após o desconto está correto");
    }

    @Test
    public void testAplicarDescontoInvalido() {
        System.out.println("Teste de aplicação de desconto inválido...");
        assertFalse(item.aplicarDesconto(80), "Verificando se o desconto de 80% é rejeitado corretamente");
    }
}
