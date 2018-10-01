package com.arbresystems.appoint.view;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

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

            bindSummaryValue(findPreference("key_nome_usr"));
            bindSummaryValue(findPreference("key_email_usr"));
            bindSummaryValue(findPreference("key_telefone_usr"));
            bindSummaryValue(findPreference("key_tempo_config"));

        }
    }

    private static void bindSummaryValue(Preference preference){
        preference.setOnPreferenceChangeListener(listener);
        listener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    private static Preference.OnPreferenceChangeListener listener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValor = newValue.toString();
            if (preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValor);
                //seta o sumario com o novo valor
                preference.setSummary(index > 0
                    ? listPreference.getEntries()[index]
                    :null);

            }else if (preference instanceof EditTextPreference){
                preference.setSummary(stringValor);
            }
            return true;
        }
    };

}
