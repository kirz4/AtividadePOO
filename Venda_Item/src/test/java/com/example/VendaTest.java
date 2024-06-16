package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendaTest {

    private Venda venda;

    @BeforeEach
    public void setUp() {
        venda = new Venda("Cliente Teste");
    }

    @Test
    public void testClienteInicializadoCorretamente() {
        assertEquals("Cliente Teste", venda.getCliente(), "Verificando se o cliente foi inicializado corretamente");
    }

    @Test
    public void testSituacaoInicialNaoIniciada() {
        assertEquals(Venda.SITUACAO_NAO_INICIADA, venda.getSituacao(), "Verificando se a situação inicial é 'NÃO INICIADA'");
    }

    @Test
    public void testVenderAlteraSituacaoParaEmAndamento() {
        venda.vender("Produto Teste", 10.0, 2.0);
        assertEquals(Venda.SITUACAO_EM_ANDAMENTO, venda.getSituacao(), "Verificando se a situação muda para 'EM ANDAMENTO' após vender um produto");
    }

    @Test
    public void testFinalizarVendaAlteraSituacaoParaEncerrada() {
        venda.vender("Produto Teste", 10.0, 2.0);
        venda.finalizar();
        assertEquals(Venda.SITUACAO_ENCERRADA, venda.getSituacao(), "Verificando se a situação muda para 'ENCERRADA' após finalizar a venda");
    }

    @Test
    public void testAplicarDescontoSomenteQuandoEmAndamento() {
        venda.vender("Produto Teste", 10.0, 2.0);
        assertAll("Verificando aplicação de desconto em diferentes situações",
                () -> assertTrue(venda.aplicarDesconto(10), "Verificando se o desconto de 10% é aplicado quando a venda está em andamento"),
                () -> {
                    venda.finalizar();
                    assertFalse(venda.aplicarDesconto(10), "Verificando se o desconto não é aplicado quando a venda está encerrada");
                }
        );
    }

    @Test
    public void testAplicarDescontoInvalido() {
        venda.vender("Produto Teste", 10.0, 2.0);
        assertFalse(venda.aplicarDesconto(-5), "Verificando se o desconto negativo é rejeitado corretamente");
    }
}
