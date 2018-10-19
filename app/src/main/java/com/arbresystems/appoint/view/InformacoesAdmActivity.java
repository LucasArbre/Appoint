package com.arbresystems.appoint.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arbresystems.appoint.R;
import com.arbresystems.appoint.model.Estabelecimento;

public class InformacoesAdmActivity extends AppCompatActivity {

    private Estabelecimento estabelecimento;

    private TextView txtNome;
    private TextView txtDescricao;
    private TextView txtDataEntrada;
    private RatingBar rbAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacoes_adm);

        Intent intent = getIntent();
        estabelecimento = (Estabelecimento) intent.getSerializableExtra("estabelecimento");

        txtDescricao = findViewById(R.id.txtDescricao);
        txtNome = findViewById(R.id.txtNome);
        txtDataEntrada = findViewById(R.id.txtDataEntrada);
        rbAvaliacao = findViewById(R.id.rbAvaliacao);

        txtDescricao.setText(estabelecimento.getDescricaoEstabelecimento());
        txtNome.setText(estabelecimento.getNome());
        txtDataEntrada.setText("Desde " + estabelecimento.getDataEntrada());
        rbAvaliacao.setRating(estabelecimento.getAvaliacao());
    }
}
