package com.arbresystems.appoint.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arbresystems.appoint.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_inicio);
    }
}
