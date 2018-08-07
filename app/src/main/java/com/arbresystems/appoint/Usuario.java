package com.arbresystems.appoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("idUsuario")
    @Expose
    private String idUsuario;
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
    @SerializedName("ultimaLocalizacao")
    @Expose
    private String ultimaLocalizacao;
    @SerializedName("email")
    @Expose
    private String email;

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
     * @param ultimaLocalizacao
     * @param nome
     * @param descricao
     */
    public Usuario(String idUsuario, String nome, String id, String telefone, Boolean erro, String descricao, String token, String ultimaLocalizacao, String email) {
        super();
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.id = id;
        this.telefone = telefone;
        this.erro = erro;
        this.descricao = descricao;
        this.token = token;
        this.ultimaLocalizacao = ultimaLocalizacao;
        this.email = email;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
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

    public String getUltimaLocalizacao() {
        return ultimaLocalizacao;
    }

    public void setUltimaLocalizacao(String ultimaLocalizacao) {
        this.ultimaLocalizacao = ultimaLocalizacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", id='" + id + '\'' +
                ", telefone='" + telefone + '\'' +
                ", erro=" + erro +
                ", descricao='" + descricao + '\'' +
                ", token='" + token + '\'' +
                ", ultimaLocalizacao='" + ultimaLocalizacao + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}