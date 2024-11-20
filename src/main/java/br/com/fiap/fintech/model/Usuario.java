package br.com.fiap.fintech.model;

public class Usuario {
    private int codigoUsuario;
    private String email;
    private String senha;
    private String nome;

    public Usuario(int codigoUsuario, String email, String senha, String nome){
        this.codigoUsuario = codigoUsuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }
    public Usuario(String email, String senha, String nome){
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }
}
