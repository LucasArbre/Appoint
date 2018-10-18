package com.arbresystems.appoint.view;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

import com.arbresystems.appoint.R;

public class AgendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_agendar);
    }
}
