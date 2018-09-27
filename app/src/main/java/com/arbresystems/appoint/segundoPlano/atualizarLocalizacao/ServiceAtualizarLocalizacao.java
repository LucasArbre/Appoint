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
        if (localizacao.isGPSEnabled){
            Log.e("latitude", String.valueOf(localizacao.getLatitude()));
            Log.e("longitude", String.valueOf(localizacao.getLongitude()));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
