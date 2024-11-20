package br.com.fiap.fintech.model;

public class Categoria {
    private int codigoCategoria;
    private String nomeCategoria;
    private String descricaoCategoria;


    public Categoria(String nomeCategoria, String descricaoCategoria){
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }

    public Categoria(int codigoCategoria, String nomeCategoria, String descricaoCategoria){
        this.codigoCategoria = codigoCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricaoCategoria = descricaoCategoria;
    }

    public Categoria() {

    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public String getDescricaoCategoria() {
        return descricaoCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public void setDescricaoCategoria(String descricaoCategoria) {
        this.descricaoCategoria = descricaoCategoria;
    }
}
