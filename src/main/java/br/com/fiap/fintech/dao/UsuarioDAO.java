package br.com.fiap.fintech.dao;



import br.com.fiap.fintech.dao.factory.ConnectionFactory;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public Usuario pesquisarUsuario(String email, String senha) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_usuario WHERE email_usuario = ? AND senha_usuario = ?");
        stm.setString(1, email);
        stm.setString(2, senha);
        ResultSet result = stm.executeQuery();
        if (!result.next()) {
            throw new EntidadeNaoEncontradaException("Usuario não encontrado");
        } else {
            int cod_usuario = result.getInt("cod_usuario");
            String email_usuario = result.getString("email_usuario");
            String nome_usuario = result.getString("nome_usuario");

            return new Usuario(cod_usuario, email_usuario, "", nome_usuario);
        }

    }
    public void criarUsuario(Usuario usuario) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO t_fin_usuario (nome_usuario, email_usuario, senha_usuario) VALUES (?, ?, ?)");
        stm.setString(1, usuario.getNome());
        stm.setString(2, usuario.getEmail());
        stm.setString(3, usuario.getSenha());
        stm.executeUpdate();
    }


    public void fecharConexao() throws SQLException {
        connection.close();
    }
}
