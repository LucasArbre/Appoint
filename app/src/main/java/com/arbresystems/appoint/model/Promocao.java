package com.arbresystems.appoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promocao {

    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("linkImg")
    @Expose
    private String linkImg;
    @SerializedName("porcentagem")
    @Expose
    private int porcentagem;
    @SerializedName("erro")
    @Expose
    private boolean erro;

    /**
     * No args constructor for use in serialization
     *
     */
    public Promocao() {
    }

    /**
     *
     * @param porcentagem
     * @param linkImg
     * @param titulo
     * @param erro
     * @param descricao
     */
    public Promocao(String titulo, String descricao, String linkImg, int porcentagem, boolean erro) {
        super();
        this.titulo = titulo;
        this.descricao = descricao;
        this.linkImg = linkImg;
        this.porcentagem = porcentagem;
        this.erro = erro;
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

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

}