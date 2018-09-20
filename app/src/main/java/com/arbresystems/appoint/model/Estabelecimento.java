package com.arbresystems.appoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estabelecimento {

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
    @SerializedName("CNPJ")
    @Expose
    private String cNPJ;
    @SerializedName("endereco")
    @Expose
    private String endereco;
    @SerializedName("Cidade")
    @Expose
    private String cidade;
    @SerializedName("Estado")
    @Expose
    private String estado;
    @SerializedName("plano")
    @Expose
    private String plano;
    @SerializedName("erro")
    @Expose
    private boolean erro;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("idEstabelecimento")
    @Expose
    private String idEstabelecimento;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     *
     */
    public Estabelecimento() {
    }

    /**
     *
     * @param erro
     * @param cNPJ
     * @param senha
     * @param plano
     * @param descricao
     * @param idEstabelecimento
     * @param cidade
     * @param estado
     * @param token
     * @param email
     * @param telefone
     * @param nome
     * @param endereco
     */
    public Estabelecimento(String nome, String email, String telefone, String senha, String cNPJ, String endereco, String cidade, String estado, String plano, boolean erro, String descricao, String idEstabelecimento, String token) {
        super();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.cNPJ = cNPJ;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.plano = plano;
        this.erro = erro;
        this.descricao = descricao;
        this.idEstabelecimento = idEstabelecimento;
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

    public String getCNPJ() {
        return cNPJ;
    }

    public void setCNPJ(String cNPJ) {
        this.cNPJ = cNPJ;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
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

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", cNPJ='" + cNPJ + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", plano='" + plano + '\'' +
                ", erro=" + erro +
                ", descricao='" + descricao + '\'' +
                ", idEstabelecimento='" + idEstabelecimento + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}