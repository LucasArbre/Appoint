package com.arbresystems.appoint.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Estabelecimento;

public class InformacoesAdmActivity extends AppCompatActivity {

    private Estabelecimento estabelecimento;

    private TextView txtNome;
    private TextView txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_adm);

        Intent intent = getIntent();
        estabelecimento = (Estabelecimento) intent.getSerializableExtra("estabelecimento");

        txtDescricao = findViewById(R.id.txtDescricao);
        txtNome = findViewById(R.id.txtNome);

        txtDescricao.setText(estabelecimento.getDescricao());
        txtNome.setText(estabelecimento.getNome());
    }
}
