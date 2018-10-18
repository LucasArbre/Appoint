package com.arbresystems.appoint.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Estabelecimento;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PerfilAdmActivity extends AppCompatActivity {

    private Estabelecimento estabelecimento;

    private Button btnLigar;
    private ImageView imgPerfil;
    private Button btnAgendarHorario;
    private Button btnIrPara;
    private Button btnSobre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_adm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        estabelecimento = (Estabelecimento) intent.getSerializableExtra("estabelecimento");
        Log.d("estabelecimento", estabelecimento.toString());

        btnLigar = findViewById(R.id.btnLigar);
        imgPerfil = findViewById(R.id.imgPerfil);
        btnAgendarHorario = findViewById(R.id.btnAgendarHorario);
        btnIrPara = findViewById(R.id.btnIrPara);
        btnSobre = findViewById(R.id.btnSobre);

        Glide.with(this).load("http://goo.gl/gEgYUd").into(imgPerfil);

        Log.e("dia", getDia());

        btnLigar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+estabelecimento.getTelefone());
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);

                startActivity(intent);
            }
        });

        btnAgendarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AgendarActivity.class));
            }
        });

        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), InformacoesAdmActivity.class).putExtra("estabelecimento", estabelecimento));
            }
        });
    }

    private String getDia(){
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        String nome = "";
        int dia = c.get(c.DAY_OF_WEEK);
        switch(dia){
            case Calendar.SUNDAY: nome = "Domingo";break;
            case Calendar.MONDAY: nome = "Segunda";break;
            case Calendar.TUESDAY: nome = "Terça";break;
            case Calendar.WEDNESDAY: nome = "Quarta";break;
            case Calendar.THURSDAY: nome = "Quinta";break;
            case Calendar.FRIDAY: nome = "Sexta";break;
            case Calendar.SATURDAY: nome = "sábado";break;
        }
        return nome;
    }

    private String getHorario(){
        SimpleDateFormat dateFormatHora = new SimpleDateFormat("HH:mm:ss");
        Date data = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        Date dataAtual = cal.getTime();
        String horaAtual = dateFormatHora.format(dataAtual);
        Log.i("hora", horaAtual);
        return horaAtual;
    }
}
