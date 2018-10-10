package com.arbresystems.appoint.viewModels;

import com.arbresystems.appoint.model.Horario;

import java.util.ArrayList;
import java.util.Date;

public class SectionDataModelHorario {
    private Date diaMes;
    private Date dia;
    private ArrayList<Horario> allItemsInSection;


    public SectionDataModelHorario() {

    }

    public SectionDataModelHorario(Date diaMes, Date dia, ArrayList<Horario> allItemsInSection) {
        this.diaMes = diaMes;
        this.dia = dia;
        this.allItemsInSection = allItemsInSection;
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

    public ArrayList<Horario> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<Horario> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }
}
