package com.arbresystems.appoint.segundoPlano.atualizarLocalizacao;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

final class Localizacao implements LocationListener {
    private Location location;

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
