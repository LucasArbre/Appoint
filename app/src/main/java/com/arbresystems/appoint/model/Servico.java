package com.arbresystems.appoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servico {

    @SerializedName("erro")
    @Expose
    private boolean erro;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("idServico")
    @Expose
    private String idServico;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("preco")
    @Expose
    private String preco;
    @SerializedName("duracao")
    @Expose
    private String duracao;

    /**
     * No args constructor for use in serialization
     *
     */
    public Servico() {
    }

    /**
     *
     * @param duracao
     * @param preco
     * @param erro
     * @param nome
     * @param idServico
     * @param descricao
     */
    public Servico(boolean erro, String descricao, String idServico, String nome, String preco, String duracao) {
        super();
        this.erro = erro;
        this.descricao = descricao;
        this.idServico = idServico;
        this.nome = nome;
        this.preco = preco;
        this.duracao = duracao;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

}