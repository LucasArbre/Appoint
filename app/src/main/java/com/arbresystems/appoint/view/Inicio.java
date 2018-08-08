package com.arbresystems.appoint.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arbresystems.appoint.R;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_inicio);
    }
}
