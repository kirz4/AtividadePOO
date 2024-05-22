package com.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoJdbcDao {

    private Connection conexao;

    private static Connection conectarBD() {
        String dadosConexao = "jdbc:h2:~/ecommerce_db";
        try {
            return DriverManager.getConnection(dadosConexao, "lucas", "lioup098");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ProdutoJdbcDao() {
        this.conexao = conectarBD();
    }

    public void persistir(Produto produto) {
        String query = "INSERT INTO produtos (tipo, descricao, peso, quantidade, unidade_medida) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, produto.getTipo());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPeso());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getUnidadeMedida());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Produto produto) {
        String query = "UPDATE produtos SET tipo = ?, descricao = ?, peso = ?, quantidade = ?, unidade_medida = ? WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, produto.getTipo());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPeso());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setString(5, produto.getUnidadeMedida());
            stmt.setLong(6, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Produto pesquisarPorId(Long id) {
        String query = "SELECT * FROM produtos WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rsToProduto(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Long id) {
        String query = "DELETE FROM produtos WHERE id = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produtos";
        try {
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Produto produto = rsToProduto(rs);
                produtos.add(produto);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Produto rsToProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getLong("id"));
        produto.setTipo(rs.getString("tipo"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPeso(rs.getDouble("peso"));
        produto.setQuantidade(rs.getInt("quantidade"));
        produto.setUnidadeMedida(rs.getString("unidade_medida"));
        return produto;
    }

    public static void criarTabela() throws SQLException {
        Connection conn = conectarBD();
        String query = "CREATE TABLE IF NOT EXISTS produtos ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "tipo VARCHAR(50), "
                + "descricao VARCHAR(255), "
                + "peso DOUBLE, "
                + "quantidade INT, "
                + "unidade_medida VARCHAR(20)"
                + ")";
        Statement stmt = conn.createStatement();
        stmt.execute(query);
        conn.close();
    }
}

