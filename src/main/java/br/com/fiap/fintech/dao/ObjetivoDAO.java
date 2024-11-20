package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.dao.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ObjetivoDAO {
    // Por falta de tempo devido viajens a trabalho, infelizmente não consegui usar essa classe no projeto,
    // mas o "Objetivo" originalmente seria usado para atrelar as contas a um objetivo especifico, e acompanhar na dashboard.
    // Ex: Objetivo: "Viajar para a Disney", com uma meta de valor a ser batida em saldo na conta com ou sem previsão de data.


    private Connection connection;

    public ObjetivoDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void fecharConexao() throws SQLException {
        connection.close();
    }
}
