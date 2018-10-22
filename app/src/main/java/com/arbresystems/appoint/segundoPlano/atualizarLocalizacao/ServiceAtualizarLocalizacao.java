package com.arbresystems.appoint.segundoPlano.atualizarLocalizacao;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

public class ServiceAtualizarLocalizacao extends IntentService {
    private Localizacao localizacao = new Localizacao();
    private LocationManager locationManager = null;
    private Location location = null;

    public ServiceAtualizarLocalizacao() {
        super("ServiceAtualizarLocalizacao");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String bestProvider = null;
        if (locationManager != null) {
            bestProvider = locationManager.getBestProvider(new Criteria(), true);
        }

        if (localizacao != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                    0, localizacao);
        }

        if (bestProvider != null){
            location = locationManager.getLastKnownLocation(bestProvider);
        }

        if (location != null) {
            Log.d("longitude", String.valueOf(location.getLongitude()));
            Log.d("latitude", String.valueOf(String.valueOf(location.getLatitude())));
            Toast.makeText(this, "Latitude: " + location.getLatitude() + " " + "Longitude: " + location.getLongitude(), Toast.LENGTH_LONG).show();
            //Toast.makeText(this, "Longitude: " + l.getLongitude(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
