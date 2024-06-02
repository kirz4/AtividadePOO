package br.edu.idp.tech.poo;

import br.edu.idp.tech.poo.dao.CarroDao;
import br.edu.idp.tech.poo.dao.CarroJdbcDao;
import br.edu.idp.tech.poo.dao.CarroJpaDao;
import br.edu.idp.tech.poo.entidade.Carro;
import br.edu.idp.tech.poo.ui.CliIavel;



import java.util.List;

public class Programa {

    private String sufixo;
    private CliIavel ui;

    public Programa(CliIavel cli) {
        this.ui = cli;
    }

    public void executar() {
        // Marca criada para diferenciar o que é cadastrado em cada execução
        sufixo = " /" + gerarLetraAleatoria();
        ui.exibirInformacao("Sufixo da execução: " + sufixo);

        // Criação do banco de dados
        prepararBD();

        // Execução por JDBC
        ui.exibirInformacao("Execução via JDBC - início");
        executarDao(new CarroJdbcDao());
        ui.exibirInformacao("Execução via JDBC - término");

        // Execução por JPA
        ui.exibirInformacao("Execução via JPA - início");
        executarDao(new CarroJpaDao());
        ui.exibirInformacao("Execução via JPA - término");
    }

    private void executarDao(CarroDao dao) {
        // Gerador aleatório de carros para "economizar" a inclusão de dados pelo usuário
        CarregadorDados carregador = new CarregadorDados(sufixo);

        Carro novoCarro = carregador.gerarCarro();
        // Salvar carro criado
        dao.salvar(novoCarro);

        List<Carro> novosCarros = carregador.gerarListaCarros(3); // Ajuste para 3 carros, independente do DAO
        // Salvar coleção de carros
        dao.salvar(novosCarros);

        // Exibir lista de carros
        List<Carro> carros = dao.findAll();
        ui.exibirListaCarros(carros);
    }

    private void prepararBD() {
        try {
            // Criar tabela apenas se não existir
            CarroDao jdbcDao = new CarroJdbcDao();
            if (!jdbcDao.tableExists()) {
                jdbcDao.createTable();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private char gerarLetraAleatoria() {
        final int NUM_LETRA_BASE = 97;
        final int QUANTIDADE_LETRAS = 25;

        int numero = GeradorNumAleatorio.gerarInt(QUANTIDADE_LETRAS);

        return (char) (NUM_LETRA_BASE + numero);
    }
}
