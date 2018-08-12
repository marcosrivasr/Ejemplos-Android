package com.marivr.foursquareapi.Controllers;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.gson.Gson;
import com.marivr.foursquareapi.Customs.RecycleViewCustomAdapter;
import com.marivr.foursquareapi.Customs.RecyclerViewClickListener;
import com.marivr.foursquareapi.Model.Venues.Venue;
import com.marivr.foursquareapi.Model.Venues.VenuesResponse;
import com.marivr.foursquareapi.Networking.HttpRequest;
import com.marivr.foursquareapi.Networking.RequestBuilder;
import com.marivr.foursquareapi.R;
import com.marivr.foursquareapi.Util.Preferences;

import okhttp3.OkHttpClient;

public class SearchResults extends AppCompatActivity  {

    /*
     * Cliente para hacer solicitudes HTTP
     * Se usa para hacer las llamadas a la API de Foursquare
     */
    private OkHttpClient client;

    /*
     * Objeto Preferences para guardar el token u otras propiedades
     */
    private Preferences p;

    /*
     * Objeto UI para mostrar lista de lugares
     */
    private RecyclerView lista;

    /*
     *
     * Objeto para administrar la forma en que se ven los
     * elementos en un RecyclerView
     */
    private LinearLayoutManager linearLayoutManager;

    /*
     * Objeto con la respuesta que se va a serializar con la librería gson
     * para obtener toda la estructura del JSON mapeada a una clase
     */
    private VenuesResponse venues;

    /*
     *
     * Implementación para el listener del RecylclerView
     */
    private RecyclerViewClickListener listener;

    /*
     *
     * Variables para guardar las coordenadas de la actividad pasada
     */
    private String latitud, longitud;

    /*
     *  Constante para poder marcar el intent para pasar elemento
     */
    public static final String ITEM = "com.marivr.fousquareapi.ITEM";


    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        latitud = getIntent().getStringExtra("latitud");
        longitud = getIntent().getStringExtra("longitud");


        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);

        listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Venue item = venues.getVenues().getVenues().get(position);
                Log.i("listener", item.getCategories().get(0).getIcon().getPrefix());
                Gson gson = new Gson();
                String itemString = gson.toJson(item);
                Intent intent = new Intent(SearchResults.this, Detail.class);
                intent.putExtra(ITEM, itemString);
                startActivity(intent);
            }
        };

        client = new OkHttpClient();
        p = new Preferences(this);

        new AsyncTask<Void, Void, Void>() {
            String response = "";
            String responseProfiles;
            @Override
            protected Void doInBackground(Void... params) {
                response = HttpRequest.GET(client, RequestBuilder.buildURL(p.getToken(), latitud, longitud));
                Log.d("loadContent", response);
                return null;
            }

            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if(response == "" || response == null){
                    Toast.makeText(SearchResults.this, "Se produjo un error al obtener la información", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = new Gson();
                    venues = gson.fromJson(response, VenuesResponse.class);

                    RecycleViewCustomAdapter adapter  = new RecycleViewCustomAdapter(venues.getVenues().getVenues(),listener, SearchResults.this, p.getToken());
                    lista.setAdapter(adapter);    
                }
                
            }

            @Override
            protected void onPreExecute() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(SearchResults.this);
                    progressDialog.setMessage(getString(R.string.loading_message));
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setCancelable(false);
                }

            }
        }.execute();


    }

    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
    }

}
