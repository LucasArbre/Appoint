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
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("erro")
    @Expose
    private Boolean erro;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;

    /**
     * No args constructor for use in serialization
     *
     */
    public Usuario() {
    }

    /**
     *
     * @param id
     * @param idUsuario
     * @param erro
     * @param email
     * @param telefone
     * @param nome
     * @param descricao
     */
    public Usuario(String nome, String id, String telefone, String email, Boolean erro, String descricao, Integer idUsuario) {
        super();
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.email = email;
        this.erro = erro;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

}