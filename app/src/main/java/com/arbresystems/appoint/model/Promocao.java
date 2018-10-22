package com.arbresystems.appoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Promocao {

    @SerializedName("idEstabelecimento")
    @Expose
    private String idEstabelecimento;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("endereco")
    @Expose
    private String endereco;
    @SerializedName("CEP")
    @Expose
    private Object cEP;
    @SerializedName("estado")
    @Expose
    private Object estado;
    @SerializedName("cidade")
    @Expose
    private Object cidade;
    @SerializedName("CNPJ")
    @Expose
    private String cNPJ;
    @SerializedName("telefone")
    @Expose
    private String telefone;
    @SerializedName("linkImgPerfil")
    @Expose
    private String linkImgPerfil;
    @SerializedName("avaliacao")
    @Expose
    private String avaliacao;
    @SerializedName("dataEntrada")
    @Expose
    private String dataEntrada;
    @SerializedName("descricaoEstabelecimento")
    @Expose
    private String descricaoEstabelecimento;
    @SerializedName("idPromocao")
    @Expose
    private String idPromocao;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("porcentagem")
    @Expose
    private String porcentagem;
    @SerializedName("linkImg")
    @Expose
    private Object linkImg;
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
     * @param descricaoEstabelecimento
     * @param idPromocao
     * @param cEP
     * @param linkImgPerfil
     * @param erro
     * @param cNPJ
     * @param descricao
     * @param avaliacao
     * @param dataEntrada
     * @param linkImg
     * @param idEstabelecimento
     * @param titulo
     * @param cidade
     * @param estado
     * @param telefone
     * @param nome
     * @param endereco
     */
    public Promocao(String idEstabelecimento, String nome, String endereco, Object cEP, Object estado, Object cidade, String cNPJ, String telefone, String linkImgPerfil, String avaliacao, String dataEntrada, String descricaoEstabelecimento, String idPromocao, String titulo, String descricao, String porcentagem, Object linkImg, boolean erro) {
        super();
        this.idEstabelecimento = idEstabelecimento;
        this.nome = nome;
        this.endereco = endereco;
        this.cEP = cEP;
        this.estado = estado;
        this.cidade = cidade;
        this.cNPJ = cNPJ;
        this.telefone = telefone;
        this.linkImgPerfil = linkImgPerfil;
        this.avaliacao = avaliacao;
        this.dataEntrada = dataEntrada;
        this.descricaoEstabelecimento = descricaoEstabelecimento;
        this.idPromocao = idPromocao;
        this.titulo = titulo;
        this.descricao = descricao;
        this.porcentagem = porcentagem;
        this.linkImg = linkImg;
        this.erro = erro;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Object getCEP() {
        return cEP;
    }

    public void setCEP(Object cEP) {
        this.cEP = cEP;
    }

    public Object getEstado() {
        return estado;
    }

    public void setEstado(Object estado) {
        this.estado = estado;
    }

    public Object getCidade() {
        return cidade;
    }

    public void setCidade(Object cidade) {
        this.cidade = cidade;
    }

    public String getCNPJ() {
        return cNPJ;
    }

    public void setCNPJ(String cNPJ) {
        this.cNPJ = cNPJ;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLinkImgPerfil() {
        return linkImgPerfil;
    }

    public void setLinkImgPerfil(String linkImgPerfil) {
        this.linkImgPerfil = linkImgPerfil;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDescricaoEstabelecimento() {
        return descricaoEstabelecimento;
    }

    public void setDescricaoEstabelecimento(String descricaoEstabelecimento) {
        this.descricaoEstabelecimento = descricaoEstabelecimento;
    }

    public String getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(String idPromocao) {
        this.idPromocao = idPromocao;
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

    public String getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(String porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Object getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(Object linkImg) {
        this.linkImg = linkImg;
    }

    public boolean isErro() {
        return erro;
    }

    public void setErro(boolean erro) {
        this.erro = erro;
    }

}