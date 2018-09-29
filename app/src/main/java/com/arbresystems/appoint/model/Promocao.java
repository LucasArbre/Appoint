package com.arbresystems.appoint.model;

public class Promocao {
    private String pocentagem;
    private String titulo;
    private String descricao;

    public Promocao(String pocentagem, String titulo, String descricao) {
        this.pocentagem = pocentagem;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getPocentagem() {
        return pocentagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setPocentagem(String pocentagem) {
        this.pocentagem = pocentagem;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
