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
}
