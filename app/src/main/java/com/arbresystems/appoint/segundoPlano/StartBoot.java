package com.arbresystems.appoint.segundoPlano;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class StartBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        Intent myIntent = new Intent(context, ServiceStart.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(myIntent);
        }else{
            context.startService(myIntent);
        }
    }
}