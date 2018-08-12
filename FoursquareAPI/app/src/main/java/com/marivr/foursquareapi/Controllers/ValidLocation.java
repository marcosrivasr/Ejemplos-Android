package com.marivr.foursquareapi.Controllers;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.marivr.foursquareapi.Networking.RequestBuilder;
import com.marivr.foursquareapi.R;

import org.w3c.dom.Text;



public class ValidLocation extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    /*
     * *********************************************************************
     *  VARIABLES PARA OBTENER UBICACIÓN Y COORDENADAS
      * *********************************************************************
     */
    private GoogleApiClient mGoogleApiClient; // Cliente de Google API
    private Location location;           // Objeto para obtener ubicación
    private final int REQUEST_LOCATION = 1;   // Código de petición para ubicación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_valid_location);

        // TODO: 3.- Inicializar cliente de Google API
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    /*
    ********************************************************************************
    *   METODOS PARA IMPLEMENTAR EL OBTENER UBICACIÓN
    * ******************************************************************************
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("onConnected", "conectado");
        processLocation();
    }

    /**
     * Método inicial para poder obtener la ubicación,
     *
     */
    private void processLocation() {
        // Se trata de obtener la última ubicación registrada
        getLocation();

        // Si ubicación es diferente de nulo se actualizan los campos para escribir la ubicación
        if (location != null) {
            updateLocationUI();
        }
    }

    /**
     * Valida que haya permisos explícitos por el usuario
     * para poder continuar con el proceso
     * si no se dar el permiso se manda a llamar deniedPermission()
     * quien administra el error que se le presenta al usuario
     */
    private void getLocation() {
        // Se valida que haya permisos garantizados
        if (isLocationPermissionGranted()) {
            // Si los hay se regresa un objeto con información de ubicacion
            location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        } else {
            // Sino se administra la cancelación de permisos
            deniedPermission();
        }
    }

    /**
     * Valida que haya permisos para poder obtener ubicación
     * @return si está autorizada la app para obtener ubicación
     */
    private boolean isLocationPermissionGranted() {
        int permission = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;

    }

    /**
     * Si el usuario se ha negado a dar permisos a la app
     * para poder acceder a la ubicación se le muestra
     * algún mensaje de que rechazo la acción
     */
    private void deniedPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Aquí muestras confirmación explicativa al usuario
            // por si rechazó los permisos anteriormente
            toastMessage(this, getString(R.string.access_error));
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }

    /**
     * Se asignan valores de longitud y latitud
     */
    private void updateLocationUI() {
        String latitud = String.valueOf(location.getLatitude());
        String longitud = String.valueOf(location.getLongitude());

        Intent intent = new Intent(this, SearchResults.class);
        intent.putExtra("latitud", latitud);
        intent.putExtra("longitud", longitud);
        startActivity(intent);
        finish();
    }

    /**
     * Este método es llamado cuando el permiso para poder acceder al servicio
     * de ubicación se ha garantizado. Lo que se hace es validar el tipo
     * de permiso y a continuación se mandar a llamar updateLocation() para
     * que se obtengan los valores y se procesa a hacer las siguientes actividades
     * @param requestCode tipo de solicitud
     * @param permissions tipo de permiso asignado
     * @param grantResults resultados
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                if (location != null) {
                    // Se obtienen las coordenadas
                    updateLocationUI();
                }else {
                    Toast.makeText(this, "Ubicación no encontrada", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Permisos no otorgados", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
