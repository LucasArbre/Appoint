package com.arbresystems.appoint.model;

import java.util.Date;

public class Horario{
    private Date horaInicio;
    private Date horaTermino;
    private Date dia;
    private String servico;

    public Horario(Date horaInicio, Date horaTermino, Date dia, String servico) {
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.dia = dia;
        this.servico = servico;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }
}
