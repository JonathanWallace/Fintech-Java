package br.com.fiap.fintech.model;

import java.sql.Timestamp;
import java.util.Calendar;

public class Transacao {
    private int codTransacao;
    private int codConta;
    private Categoria contaCategoria;
    private Timestamp dt_transacao;
    private double vlr_transacao;
    private String descTransacao;
    private String tipo_transacao;

    public Transacao(int cod_transacao, int cod_conta, Categoria contaCategoria, Timestamp dt_transacao, double vlr_transacao, String desc_transacao, String tipo_transacao) {
        this.codTransacao = cod_transacao;
        this.codConta = cod_conta;
        this.contaCategoria = contaCategoria;
        this.dt_transacao = dt_transacao;
        this.vlr_transacao = vlr_transacao;
        this.tipo_transacao = tipo_transacao;
        this.descTransacao = desc_transacao;
    }
    public Transacao(int cod_conta, Categoria contaCategoria, Timestamp dt_transacao, double vlr_transacao, String desc_transacao, String tipo_transacao) {

        this.codConta = cod_conta;
        this.contaCategoria = contaCategoria;
        this.dt_transacao = dt_transacao;
        this.vlr_transacao = vlr_transacao;
        this.tipo_transacao = tipo_transacao;
        this.descTransacao = desc_transacao;
    }

    public Transacao(int cod_conta, Categoria contaCategoria, double vlr_transacao, String desc_transacao, String tipo_transacao) {
        this.codConta = cod_conta;
        this.contaCategoria = contaCategoria;

        Calendar calendario = Calendar.getInstance();
        Timestamp dataHoraAtual = new Timestamp(calendario.getTimeInMillis());

        this.dt_transacao = dataHoraAtual;
        this.vlr_transacao = vlr_transacao;
        this.descTransacao = desc_transacao;
        this.tipo_transacao = tipo_transacao;
    }

    public int getCodTransacao() {
        return codTransacao;
    }

    public void setCodTransacao(int codTransacao) {
        this.codTransacao = codTransacao;
    }

    public int getCodConta() {
        return codConta;
    }

    public void setCodConta(int codConta) {
        this.codConta = codConta;
    }

    public Categoria getContaCategoria() {
        return contaCategoria;
    }

    public void setContaCategoria(Categoria contaCategoria) {
        this.contaCategoria = contaCategoria;
    }

    public Timestamp getDt_transacao() {
        return dt_transacao;
    }

    public void setDt_transacao(Timestamp dt_transacao) {
        this.dt_transacao = dt_transacao;
    }

    public double getVlr_transacao() {
        return vlr_transacao;
    }

    public void setVlr_transacao(double vlr_transacao) {
        this.vlr_transacao = vlr_transacao;
    }

    public String getDescTransacao() {
        return descTransacao;
    }

    public void setDescTransacao(String descTransacao) {
        this.descTransacao = descTransacao;
    }

    public String getTipo_transacao() {
        return tipo_transacao;
    }

    public void setTipo_transacao(String tipo_transacao) {
        this.tipo_transacao = tipo_transacao;
    }
}
