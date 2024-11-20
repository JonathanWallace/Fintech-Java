package br.com.fiap.fintech.model;

import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.EntidadeNaoEncontradaException;

import java.sql.SQLException;
import java.util.List;

public class Conta {
    private int codigoConta;
    private int codigoUsuario;
    private String nomeConta;
    private double saldoConta;
    private String tipoConta;


    public Conta(int codigoConta, int codigoUsuario, String nomeConta, String tipoConta){
        this.codigoConta = codigoConta;
        this.codigoUsuario = codigoUsuario;
        this.nomeConta = nomeConta;
        this.saldoConta = 0.00;
        this.tipoConta = tipoConta;

    }
    public Conta(int codigoUsuario, String nomeConta, String tipoConta){
        this.codigoUsuario = codigoUsuario;
        this.nomeConta = nomeConta;
        this.saldoConta = 0.00;
        this.tipoConta = tipoConta;

    }

    public Conta(int codigoUsuario, String nomeConta,Double saldoConta, String tipoConta){
        this.codigoUsuario = codigoUsuario;
        this.nomeConta = nomeConta;
        this.saldoConta = saldoConta;
        this.tipoConta = tipoConta;

    }

    public Conta(int codConta,int codigoUsuario, String nomeConta,Double saldoConta, String tipoConta) throws SQLException {
        this.codigoConta = codConta;
        this.codigoUsuario = codigoUsuario;
        this.nomeConta = nomeConta;

        try{
            TransacaoDAO transacaoDAO = new TransacaoDAO();
            List<Transacao> transacoes = transacaoDAO.listarTransacoes(this);
            transacaoDAO.fecharConexao();
            for (Transacao transacao : transacoes) {
                if (transacao.getTipo_transacao().equalsIgnoreCase("DESPESA")){
                    this.saldoConta -= transacao.getVlr_transacao();
                }else{
                    this.saldoConta += transacao.getVlr_transacao();
                }
            }
        } catch (EntidadeNaoEncontradaException e) {
            throw new RuntimeException(e);
        }

        this.tipoConta = tipoConta;

    }

    public int getCodigoConta() {
        return codigoConta;
    }

    public void setCodigoConta(int codigoConta) {
        this.codigoConta = codigoConta;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }
}
