package com.arbresystems.appoint.view;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.arbresystems.appoint.R;

public class ConfigActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //carrega o fragmento
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new Configuracoes()).commit();
    }

    public static class Configuracoes extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
