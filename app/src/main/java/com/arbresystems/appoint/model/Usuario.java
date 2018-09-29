package com.arbresystems.appoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable {

    @SerializedName("erro")
    @Expose
    private boolean erro;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("idUsuario")
    @Expose
    private String idUsuario;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("dataNascimento")
    @Expose
    private String dataNascimento;
    @SerializedName("id")
    @Expose
    private String id;

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
     * @param email
     * @param token
     * @param erro
     * @param telefone
     * @param dataNascimento
     * @param nome
     * @param descricao
     */
    public Usuario(boolean erro, String descricao, String idUsuario, String token, String nome, String email, String telefone, String dataNascimento, String id) {
        super();
        this.erro = erro;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.id = id;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "erro=" + erro +
                ", descricao='" + descricao + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", token='" + token + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}