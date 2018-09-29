package com.arbresystems.appoint.segundoPlano;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.arbresystems.appoint.model.Usuario;
import com.arbresystems.appoint.segundoPlano.atualizarConfiguracoes.ServiceAtualizarConfiguracoes;

import static com.arbresystems.appoint.view.MainActivity.PREF_NAME;

public class ServiceStart extends IntentService {

    public ServiceStart() {
        super("ServiceStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences spM = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        StringBuilder infoM = new StringBuilder();
        infoM.append("Nome: " + spM.getString("key_nome_usr", ""));
        infoM.append("\nEmail: " + spM.getString("key_email_usr", ""));
        infoM.append("\nTelefone: " + spM.getString("key_telefone_usr", ""));
        Log.d("spM", String.valueOf(infoM));

        StringBuilder info = new StringBuilder();
        info.append("Nome: " + sp.getString("nomeUser", ""));
        info.append("\nEmail: " + sp.getString("emailUser", ""));
        info.append("\nTelefone: " + sp.getString("telefoneUser", ""));
        Log.d("sp", String.valueOf(info));

        if(sp.getString("nomeUser", "").equals(spM.getString("key_nome_usr", "")) && sp.getString("emailUser", "").equals(spM.getString("key_email_usr", "")) && sp.getString("telefoneUser", "").equals(spM.getString("key_telefone_usr", ""))){
            Log.d("usuarioAlterado", "não");
        }else{
            Log.d("usuarioAlterado", "sim");
            Usuario usuario = new Usuario();
            if (spM.getString("key_nome_usr", "").equals("")){
                usuario.setNome(sp.getString("nomeUser", ""));
            }else{
                usuario.setNome(spM.getString("key_nome_usr", ""));
            }
            startService(new Intent(this, ServiceAtualizarConfiguracoes.class).putExtra("usuario", usuario));
        }

        Log.d("ServiceStart", "Start");
    }
}
