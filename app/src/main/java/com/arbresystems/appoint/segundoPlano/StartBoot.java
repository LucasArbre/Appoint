package com.arbresystems.appoint.segundoPlano;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartBoot extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        Intent myIntent = new Intent(context, ServiceStart.class);
        context.startService(myIntent);
    }
}