package com.arbresystems.appoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Atendimento {

    @SerializedName("idEstabelecimento")
    @Expose
    private String idEstabelecimento;
    @SerializedName("dataHorarioInicio")
    @Expose
    private Date dataHorarioInicio;
    @SerializedName("dataHorarioFim")
    @Expose
    private Date dataHorarioFim;
    @SerializedName("valor")
    @Expose
    private int valor;
    @SerializedName("concluido")
    @Expose
    private int concluido;
    @SerializedName("aceito")
    @Expose
    private int aceito;
    @SerializedName("avisoEmMinutos")
    @Expose
    private int avisoEmMinutos;
    @SerializedName("erro")
    @Expose
    private boolean erro;
    @SerializedName("descricao")
    @Expose
    private String descricao;
    @SerializedName("idAtendimento")
    @Expose
    private String idAtendimento;
    @SerializedName("dia")
    @Expose
    private Date dia;

    /**
     * No args constructor for use in serialization
     *
     */
    public Atendimento() {
    }

    /**
     *
     * @param idEstabelecimento
     * @param aceito
     * @param erro
     * @param valor
     * @param dataHorarioInicio
     * @param dataHorarioFim
     * @param idAtendimento
     * @param avisoEmMinutos
     * @param descricao
     * @param concluido
     */
    public Atendimento(String idEstabelecimento, Date dataHorarioInicio, Date dataHorarioFim, int valor, int concluido, int aceito, int avisoEmMinutos, boolean erro, String descricao, String idAtendimento, Date dia) {
        super();
        this.idEstabelecimento = idEstabelecimento;
        this.dataHorarioInicio = dataHorarioInicio;
        this.dataHorarioFim = dataHorarioFim;
        this.valor = valor;
        this.concluido = concluido;
        this.aceito = aceito;
        this.avisoEmMinutos = avisoEmMinutos;
        this.erro = erro;
        this.descricao = descricao;
        this.idAtendimento = idAtendimento;
    }

    public String getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(String idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public Date getDataHorarioInicio() {
        return dataHorarioInicio;
    }

    public void setDataHorarioInicio(Date dataHorarioInicio) {
        this.dataHorarioInicio = dataHorarioInicio;
    }

    public Date getDataHorarioFim() {
        return dataHorarioFim;
    }

    public void setDataHorarioFim(Date dataHorarioFim) {
        this.dataHorarioFim = dataHorarioFim;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getConcluido() {
        return concluido;
    }

    public void setConcluido(int concluido) {
        this.concluido = concluido;
    }

    public int getAceito() {
        return aceito;
    }

    public void setAceito(int aceito) {
        this.aceito = aceito;
    }

    public int getAvisoEmMinutos() {
        return avisoEmMinutos;
    }

    public void setAvisoEmMinutos(int avisoEmMinutos) {
        this.avisoEmMinutos = avisoEmMinutos;
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

    public String getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(String idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "idEstabelecimento='" + idEstabelecimento + '\'' +
                ", dataHorarioInicio='" + dataHorarioInicio + '\'' +
                ", dataHorarioFim='" + dataHorarioFim + '\'' +
                ", valor=" + valor +
                ", concluido=" + concluido +
                ", aceito=" + aceito +
                ", avisoEmMinutos=" + avisoEmMinutos +
                ", erro=" + erro +
                ", descricao='" + descricao + '\'' +
                ", idAtendimento='" + idAtendimento + '\'' +
                '}';
    }
}