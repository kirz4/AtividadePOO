package br.edu.idp.tech.poo.dao;

import br.edu.idp.tech.poo.entidade.Carro;
import br.edu.idp.tech.poo.mapper.CarroMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroJdbcDao implements CarroDao {
    private Connection conexao;

    public CarroJdbcDao() {
        this.conexao = conectarBD();
    }

    private static Connection conectarBD() {
        String dadosConexao = "jdbc:h2:~/nosso_bd2";
        try {
            Connection connection = DriverManager.getConnection(dadosConexao, "lucas", "lioup098");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void salvar(Carro carro) {
        String query = "insert into tb_carros (marca, modelo, ano_modelo) values (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, carro.getMarca());
            stmt.setString(2, carro.getModelo());
            stmt.setInt(3, carro.getAnoModelo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void salvar(List<Carro> novosCarros) {
        String query = "insert into tb_carros (marca, modelo, ano_modelo) values (?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            for (Carro carro : novosCarros) {
                stmt.setString(1, carro.getMarca());
                stmt.setString(2, carro.getModelo());
                stmt.setInt(3, carro.getAnoModelo());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carrosDoBanco = new ArrayList<>();
        String query = "select * from tb_carros";
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Carro carro = CarroMapper.rsToCarro(rs);
                carrosDoBanco.add(carro);
            }
            rs.close();
            return carrosDoBanco;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean tableExists() {
        try {
            DatabaseMetaData metaData = conexao.getMetaData();
            ResultSet rs = metaData.getTables(null, null, "tb_carros", null);
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createTable() {
        String query = "create table if not exists tb_carros (id bigint generated always as identity primary key, marca varchar(20), modelo varchar(20), ano_modelo integer)";
        try {
            Statement stmt = conexao.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
