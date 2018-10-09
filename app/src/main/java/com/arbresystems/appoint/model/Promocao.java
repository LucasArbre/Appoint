package com.arbresystems.appoint.model;

public class Promocao {
    private String pocentagem;
    private String titulo;
    private String descricao;
    private String local;

    public String getPocentagem() {
        return pocentagem;
    }

    public void setPocentagem(String pocentagem) {
        this.pocentagem = pocentagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Promocao(String pocentagem, String titulo, String descricao, String local) {

        this.pocentagem = pocentagem;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
    }
}
