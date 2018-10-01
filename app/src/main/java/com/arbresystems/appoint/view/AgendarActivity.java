package com.arbresystems.appoint.view;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

import com.arbresystems.appoint.R;

public class AgendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
    }
}
