package com.arbresystems.appoint.viewModels;

import com.arbresystems.appoint.model.Atendimento;
import com.arbresystems.appoint.model.Horario;

import java.util.ArrayList;
import java.util.Date;

public class SectionDataModelHorario {
    private Date diaMes;
    private Date dia;
    private ArrayList<Atendimento> atendimentos;


    public SectionDataModelHorario() {

    }

    public SectionDataModelHorario(Date diaMes, Date dia, ArrayList<Atendimento> allItemsInSection) {
        this.diaMes = diaMes;
        this.dia = dia;
        this.atendimentos = atendimentos;
    }

    public Date getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(Date diaMes) {
        this.diaMes = diaMes;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public ArrayList<Atendimento> getAllItemsInSection() {
        return atendimentos;
    }

    public void setAllItemsInSection(ArrayList<Atendimento> atendimentos) {
        this.atendimentos = atendimentos;
    }
}
