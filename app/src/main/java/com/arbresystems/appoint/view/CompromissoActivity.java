package com.arbresystems.appoint.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arbresystems.appoint.R;

public class CompromissoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compromisso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
