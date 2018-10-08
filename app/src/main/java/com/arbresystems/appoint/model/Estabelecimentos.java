package com.arbresystems.appoint.model;

public class Estabelecimentos {
    private String nome;
    private String localizacao;
    private String foto;
    private String tipo;
    private int status;

    public Estabelecimentos(String nome, String localizacao, String foto, int status) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.foto = foto;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
