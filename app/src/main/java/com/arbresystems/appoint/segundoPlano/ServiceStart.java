package com.arbresystems.appoint.segundoPlano;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

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
        Log.d("ServiceStart", "Start");
    }
}
