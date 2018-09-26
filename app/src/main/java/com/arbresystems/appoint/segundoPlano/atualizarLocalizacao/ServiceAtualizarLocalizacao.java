package com.arbresystems.appoint.segundoPlano.atualizarLocalizacao;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ServiceAtualizarLocalizacao extends IntentService {

    public ServiceAtualizarLocalizacao() {
        super("ServiceAtualizarLocalizacao");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GetLocalizacao localizacao = new GetLocalizacao(getApplicationContext());
        Log.e("localizacao", String.valueOf(localizacao.getLocalizacao()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
