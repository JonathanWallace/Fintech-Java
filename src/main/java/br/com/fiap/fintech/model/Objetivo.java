package br.com.fiap.fintech.model;

import java.util.Date;

public class Objetivo {
    // Por falta de tempo devido viajens a trabalho, infelizmente não consegui usar essa classe no projeto,
    // mas o "Objetivo" originalmente seria usado para atrelar as contas a um objetivo especifico, e acompanhar na dashboard.
    // Ex: Objetivo: "Viajar para a Disney", com uma meta de valor a ser batida em saldo na conta com ou sem previsão de data.


    private int cod_conta;
    private String nomeObjetivo;
    private String descricao;
    private double valorObjetivo;
    private Date dataObjetivo;

    public Objetivo(String nomeObjetivo, String descricao, double valor){
        this.nomeObjetivo = nomeObjetivo;
        this.descricao = descricao;
        this.valorObjetivo = valor;
    }

    public Objetivo(int cod_conta, String nomeObjetivo, String descricao, double valor){
        this.cod_conta = cod_conta;
        this.nomeObjetivo = nomeObjetivo;
        this.descricao = descricao;
        this.valorObjetivo = valor;
    }

    public String getNomeObjetivo() {
        return nomeObjetivo;
    }

    public void setNomeObjetivo(String nomeObjetivo) {
        this.nomeObjetivo = nomeObjetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }
    public int getCod_conta() {
        return cod_conta;
    }
}
