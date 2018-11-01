package com.arbresystems.appoint.view;

import android.support.v4.content.ContextCompat;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Promocao;



public class CoresPromocao {
    int colorlight;
    int colordark;

    public CoresPromocao(int colorlight, int colordark) {
        this.colorlight = colorlight;
        this.colordark = colordark;
    }

    public void setColorlight(int colorlight) {
        this.colorlight = colorlight;
    }

    public void setColordark(int colordark) {
        this.colordark = colordark;
    }

    public void defineCor(Promocao promocao){
        String valor = promocao.getPorcentagem();
        int porcentagem = Integer.valueOf(valor);

        if (porcentagem<=15){

        }else if (porcentagem<=25){

        }else if (porcentagem<=50){

        }else if(porcentagem<=75){

        }else if (porcentagem>75){

        }
    }
}
