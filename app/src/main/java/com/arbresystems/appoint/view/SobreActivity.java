package com.arbresystems.appoint.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arbresystems.appoint.R;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        getSupportActionBar().hide();
    }
}
