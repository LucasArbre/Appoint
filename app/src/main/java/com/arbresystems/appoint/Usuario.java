package com.arbresystems.appoint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {

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
    private String CPF;
    @SerializedName("dataNascimento")
    @Expose
    private String dataNascimento;
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
     */
    public Usuario() {
    }

    /**
     * @param email
     * @param telefone
     * @param dataNascimento
     * @param nome
     * @param senha
     * @param CPF
     */
    public Usuario(String nome, String email, String telefone, String senha, String CPF, String dataNascimento) {
        super();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

    /**
     * @param token
     * @param erro
     * @param descricao
     */

    public Usuario(Boolean erro, String descricao, String token) {
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
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", CPF='" + CPF + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", erro=" + erro +
                ", descricao='" + descricao + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}