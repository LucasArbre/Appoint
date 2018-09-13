package com.arbresystems.appoint.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arbresystems.appoint.R;



public class PromocoesFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PromocoesFragment() {
        // Required empty public constructor
    }


    private Button btnTeste;
    private TextView txtDados;
    private Button btnDados;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);/*
        btnDados = btnDados.findViewById(R.id.btnDados);
        btnTeste = btnTeste.findViewById(R.id.btnTeste);
        txtDados = txtDados.findViewById(R.id.textDados);

        btnTeste.setOnClickListener(new View.OnClickListener() {
            FragmentActivity act = getActivity();
            @Override
            public void onClick(View v) {
                startActivity(new Intent(act, ConfigActivity.class));
            }
        });

        btnDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                StringBuilder info = new StringBuilder();
                info.append("Nome: " + sharedPreferences.getString("key_nome_usr", ""));
                info.append("\nEmail: "+sharedPreferences.getString("key_email_usr", ""));
                info.append("\nTelefone: "+sharedPreferences.getString("key_telefone_usr", ""));
                info.append("\nNotificacoes "+sharedPreferences.getBoolean("key_notificacoes_habilitar", true));
                info.append("\nTempo de Antecedencia de Notificacoes: "+sharedPreferences.getString("key_tempo_config", "-1"));

                txtDados.setText(info);
            }
        });*/
    }

}
