package com.arbresystems.appoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("erro")
    @Expose
    private Boolean erro;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public Usuario() {
    }

    /**
     *
     * @param id
     * @param token
     * @param erro
     * @param telefone
     * @param nome
     * @param descricao
     */
    public Usuario(String nome, String id, String telefone, Boolean erro, String descricao, String token) {
        super();
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.erro = erro;
        this.descricao = descricao;
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getErro() {
        return erro;
    }

    public void setErro(Boolean erro) {
        this.erro = erro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", telefone='" + telefone + '\'' +
                ", erro=" + erro +
                ", descricao='" + descricao + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}