package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.dao.factory.ConnectionFactory;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;
import br.com.fiap.fintech.model.Categoria;
import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Transacao;

import javax.xml.stream.events.EndDocument;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAO {
    private Connection connection;

    public TransacaoDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void cadastrarTransacao(Transacao transacao) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO t_fin_transacao (cod_conta, cod_categoria, dt_transacao, vlr_transacao, desc_transacao, tipo_transacao) VALUES (?, ?, ?, ?, ?, ?)");
        stm.setInt(1, transacao.getCodConta());
        stm.setInt(2, transacao.getContaCategoria().getCodigoCategoria());
        stm.setTimestamp(3, transacao.getDt_transacao());
        stm.setDouble(4, transacao.getVlr_transacao());
        stm.setString(5, transacao.getDescTransacao());
        stm.setString(6, transacao.getTipo_transacao());
        stm.executeUpdate();
    }

    private Transacao parseTransacao(ResultSet result) throws SQLException, EntidadeNaoEncontradaException {
        int cod_transacao = result.getInt("cod_transacao");
        int cod_conta = result.getInt("cod_conta");
        int cod_categoria = result.getInt("cod_categoria");
        Timestamp dt_transacao = result.getTimestamp("dt_transacao");
        double vlr_transacao = result.getDouble("vlr_transacao");
        String desc_transacao = result.getString("desc_transacao");
        String tipo_transacao = result.getString("tipo_transacao");

        Categoria categoria = new Categoria("NENHUMA", "");
        if (cod_categoria > 0){
            CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            categoria = categoriaDAO.pesquisarCategoria(cod_categoria);
        }

        return new Transacao(cod_transacao, cod_conta, categoria, dt_transacao, vlr_transacao, desc_transacao, tipo_transacao);
    }

    public Transacao pesquisarTransacao(int cod_transacao) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_transacao WHERE cod_transacao = ?");
        stm.setInt(1, cod_transacao);
        ResultSet result = stm.executeQuery();
        if(!result.next()) throw new EntidadeNaoEncontradaException("Transação não encontrada!");
        return parseTransacao(result);
    }

    public List<Transacao> listarTransacoes(Conta conta) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_fin_transacao WHERE cod_conta = ?");
        stm.setInt(1, conta.getCodigoConta());
        ResultSet result = stm.executeQuery();
        List<Transacao> transacoes = new ArrayList<>();
        while (result.next()) {
            transacoes.add(parseTransacao(result));
        }
        return transacoes;
    }

    public void alterarTransacao(Transacao transacao) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("UPDATE t_fin_transacao SET cod_categoria = ?, dt_transacao = ?, vlr_transacao = ?, tipo_transacao = ? WHERE cod_transacao = ?");
        stm.setInt(1, transacao.getContaCategoria().getCodigoCategoria());
        stm.setTimestamp(2, transacao.getDt_transacao());
        stm.setDouble(3, transacao.getVlr_transacao());
        stm.setString(4, transacao.getTipo_transacao());
        stm.setInt(5, transacao.getCodTransacao());
        stm.executeUpdate();

    }

    public void deletarTransacao(int codTransacao) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM t_fin_transacao WHERE cod_transacao = ?");
        stm.setInt(1, codTransacao);
        stm.executeUpdate();
    }

    public void fecharConexao() throws SQLException {
        connection.close();
    }
}

