package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.dao.factory.ConnectionFactory;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;
import br.com.fiap.fintech.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public CategoriaDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void cadastrarCategoria(Categoria categoria) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO t_fin_categoria (nome_categoria, desc_categoria) VALUES (?,?)");
        stm.setString(1,categoria.getNomeCategoria());
        stm.setString(2, categoria.getDescricaoCategoria());
        stm.executeUpdate();

    }

    public List<Categoria> listarCategoria() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_categoria");
        ResultSet result = stm.executeQuery();
        List<Categoria> categorias = new ArrayList<Categoria>();
        while (result.next()) {
            categorias.add(new Categoria(result.getInt("cod_categoria"),result.getString("nome_categoria"),result.getString("desc_categoria")));
        }
        return categorias;
    }

    public void excluirCategoria(Categoria categoria) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM t_fin_categoria WHERE cod_categoria = ?");
        stm.setInt(1, categoria.getCodigoCategoria());
        if (stm.executeUpdate() == 0) throw new EntidadeNaoEncontradaException("Categoria não encontrada");
    }

    public Categoria pesquisarCategoria(int cod_categoria) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_categoria WHERE cod_categoria = ?");
        stm.setInt(1, cod_categoria);
        ResultSet result = stm.executeQuery();
        if(!result.next()) throw new EntidadeNaoEncontradaException("Categoria não encontrada!");

        return new Categoria(result.getInt("cod_categoria"),result.getString("nome_categoria"),result.getString("desc_categoria"));
    }

    public void fecharConexao() throws SQLException {
        connection.close();
    }
}
