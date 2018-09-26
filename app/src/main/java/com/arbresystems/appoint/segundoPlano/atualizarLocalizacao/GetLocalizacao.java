package com.arbresystems.appoint.segundoPlano.atualizarLocalizacao;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class GetLocalizacao implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private Context context;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;

    public GetLocalizacao(Context context) {
        this.context = context;

        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this) //Be aware of state of the connection
                .addOnConnectionFailedListener(this) //Be aware of failures
                .addApi(LocationServices.API)
                .build();

        //Tentando conexão com o Google API. Se a tentativa for bem sucessidade, o método onConnected() será chamado, senão, o método onConnectionFailed() será chamado.
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("conectado", "ok");
        //Google API connection has been done successfully
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }

        lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        Log.e("latitude", String.valueOf(lastLocation.getLatitude()));
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //A conexão com o Google API falhou!
        pararConexaoComGoogleApi();
    }

    private void pararConexaoComGoogleApi() {
        //Verificando se está conectado para então cancelar a conexão!
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    public Location getLocalizacao() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        return LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }
}
