package com.arbresystems.appoint.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arbresystems.appoint.R;

public class PromocaoTelaIndividualActivity extends AppCompatActivity {

    private TextView txtTitulo;
    private TextView txtDescricao;
    private String imagem;
    private int valor_promocao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocao_tela_individual);
        getSupportActionBar().hide();
    }
}
