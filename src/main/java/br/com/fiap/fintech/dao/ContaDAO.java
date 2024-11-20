package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.dao.factory.ConnectionFactory;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private Connection connection;

    public ContaDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void cadastrarConta(Conta conta) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO t_fin_conta (cod_usuario, nome_conta, saldo_conta, tipo_conta) VALUES (?, ?, ?, ?)");
        stm.setInt(1, conta.getCodigoUsuario());
        stm.setString(2, conta.getNomeConta());
        stm.setDouble(3, conta.getSaldoConta());
        stm.setString(4, conta.getTipoConta());
        stm.executeUpdate();
        stm.close();
    }

    private Conta parseConta(ResultSet result) throws SQLException{
        int cod_conta = result.getInt("cod_conta");
        int cod_usuario = result.getInt("cod_usuario");
        String nome_conta = result.getString("nome_conta");
        Double saldo_conta = result.getDouble("saldo_conta");
        String tipo_conta = result.getString("tipo_conta");

        return new Conta(cod_conta, cod_usuario, nome_conta, saldo_conta, tipo_conta);
    }

    public Conta pesquisarConta(int cod_conta, int cod_usuario) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_conta WHERE cod_conta = ? AND cod_usuario = ?");
        stm.setInt(1, cod_conta);
        stm.setInt(2, cod_usuario);
        ResultSet result = stm.executeQuery();
        if (!result.next()) throw new EntidadeNaoEncontradaException("Conta inexistente");
        return parseConta(result);
    }

    public List<Conta> listarContas(int cod_usuario) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_conta WHERE cod_usuario = ?");
        stm.setInt(1, cod_usuario);
        List<Conta> contas = new ArrayList<Conta>();
        ResultSet result = stm.executeQuery();
        while (result.next()){
            contas.add(parseConta(result));        }
        return contas;
    }

    public void deletarConta(int cod_conta) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM t_fin_conta WHERE cod_conta = ?");
        stm.setInt(1, cod_conta);
        stm.executeUpdate();
    }

    public void alterarConta(Conta conta) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE t_fin_conta SET nome_conta = ?, tipo_conta = ?  WHERE cod_usuario = ? AND cod_conta = ?");
        stm.setString(1, conta.getNomeConta());
        stm.setString(2, conta.getTipoConta());
        stm.setInt(3, conta.getCodigoUsuario());
        stm.setInt(4, conta.getCodigoConta());
        stm.executeUpdate();

    }

    public void fecharConexao() throws SQLException {
        connection.close();
    }
}
