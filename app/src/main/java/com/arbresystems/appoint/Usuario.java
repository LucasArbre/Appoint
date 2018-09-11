package com.arbresystems.appoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("senha")
    @Expose
    private String senha;
    @SerializedName("CPF")
    @Expose
    private String cPF;
    @SerializedName("dataNascimento")
    @Expose
    private String dataNascimento;
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
     * @param dataNascimento
     * @param nome
     * @param senha
     * @param descricao
     * @param cPF
     */
    public Usuario(String id, String nome, String email, String telefone, String senha, String cPF, String dataNascimento, Boolean erro, String descricao, Integer idUsuario) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cPF = cPF;
        this.dataNascimento = dataNascimento;
        this.erro = erro;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCPF() {
        return cPF;
    }

    public void setCPF(String cPF) {
        this.cPF = cPF;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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