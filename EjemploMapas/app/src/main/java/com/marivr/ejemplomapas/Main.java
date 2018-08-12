package com.marivr.ejemplomapas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Main extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;

    private GoogleApiClient googleApiClient; // Cliente de Google API
    private Location location;           // Objeto para obtener ubicación
    private final int REQUEST_LOCATION = 1;   // Código de petición para ubicación

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    /**
     *
     *
     * Mandamos a llamar el método processLocation donde vamos a validar
     * permisos, ubicación y errores
     * @param bundle
     *
     *
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        processLocation();
    }


    // TODO: 5.- obtener ubicación y validar que no esté vacío
    /**
     *
     * Obtiene la ubicación y el permiso, y adicional valida si el objeto Location es vacío o no
     * para poder actualizar la interfaz de usuario
     *
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

    // TODO 6.- Definimos el método getLocation()
    /**
     *
     *
     * Valida si la app tiene los permisos para poder obtener ubicación. Por defecto no los tiene
     * aunque se tenga declarado en el manifiesto los permisos necesarios.
     * La llamada de permisos se tiene que hacer manual, ya que te lo pide la clase para continuar
     *
     * <p>
     *     En caso de que si se den permisos para acceder a la ubicación se va a ejecutar el
     *     método sobre escrito onRequestPermissionsResult() que es donde ya podemos obtener
     *     las coordenadas para usar posteriormente
     * </p>
     *
     * <p>
     * En este caso creamos una clase isLocationPermissionGranted para manejar la parte de los permisos
     * ya que se requieren más adelante otra vez
     *</p>
     */
    private void getLocation() {
        // Se valida que haya permisos garantizados
        if (isLocationPermissionGranted()) {
            // Si los hay se regresa un objeto con información de ubicacion
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        } else {
            // Sino se administra la petición de permisos
            requestPermission();
        }
    }

    // TODO 7.- Definimos el método para saber si tenemos acceso a usar la ubicación
    /**
     *
     *
     * Maneja la condicional para saber si el usuario ha dado permiso
     * de usar la ubicación en la app. En este ejemplo se usa el condicional
     * solo con ACCESS_FINE_LOCATION
     * @return
     */
    private boolean isLocationPermissionGranted() {
        /* Valida si ya se dio permiso */
        int permission = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        /* Se regresa un valor booleano para saber si la app tiene permisos o no */
        return permission == PackageManager.PERMISSION_GRANTED;

    }

    // TODO: 8.- Pedimos permiso para poder acceder a la ubicación
    /**
     *
     * Aquí se maneja el cuadro de diálogo para que el usuario de permisos a la app
     * <p>
     * Típicamente el método regresa una respuesta booleana para saber si es necesario que el
     * desarrollador maneje la interfaz para avisarle al usuario que se negó a dar acceso
     * o no es necesario. Si es verdadero quiere decir que el usuario rechazó dar permisos. Si
     * es negativo se lanza la solicitud con requestPermissions incluyendo los permisos que
     * queremos que el usuario acepte.
     * </p>
     */
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            /*
                Aquí muestras confirmación explicativa al usuario
                por si rechazó los permisos
              */
            Toast.makeText(this, "No quisiste dar acceso a tu ubicación", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }

    // TODO: 9.- Se obtienen los valores de la ubicación
    private void updateLocationUI() {
       // tvLat.setText(String.valueOf(location.getLatitude()));
        //tvLon.setText(String.valueOf(location.getLongitude()));

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Tu Ubicación"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 19));

    }

    /**
     *
     * Cada vez que se garantice el acceso para un permiso
     * el método se activará y es donde podemos validar que nuestra solicitud
     * puede continuar. Identificamos nuestra solicitud de acceso a ubicación con
     * la constante REQUEST_LOCATION y a partir de ahí manejamos los resultados
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                /* se pide la última ubicación */
                location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

                /* Si la ubicación es diferente de nulo, es decir, se regresó la ubicación
                *   Entonces se actualiza la interfaz con los valores
                *   */
                if (location != null)
                    updateLocationUI();
                else
                    Toast.makeText(this, "Ubicación no encontrada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permisos no otorgados", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {}


    // TODO: 10.- Define los métodos de onStart y onStop, ya que son los que permiten que se active el servicio
    /*
    *
    *   LOS SIGUIENTES MÉTODOS SON IMPORTANTES PORQUE
    *   SIN ELLOS NO ES POSIBLE QUE EL CLIENTE DE GOOGLE SE INICIE
    *   PARA EMPEZAR A TRABAJAR
     */
    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }

}
