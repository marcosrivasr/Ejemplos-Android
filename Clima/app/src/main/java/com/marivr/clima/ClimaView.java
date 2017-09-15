package com.marivr.clima;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marivr.clima.Datos.Clima;
import com.marivr.clima.Datos.DummyData;
import com.marivr.clima.Datos.Parser;

import java.io.InputStream;


public class ClimaView extends AppCompatActivity {
    // TODO (HTTP): 1. Declarar variables
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;

    private TextView tvCiudad;
    private TextView tvTemperatura;
    private TextView tvDescripcion;
    private LinearLayout bg;

    private Clima clima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima);

        // Se obtiene ID de ciudad
        Intent intent = getIntent();
        String idCiudad = intent.getStringExtra(MainActivity.MENSAJE_EXTRA);

        // Se asocian los elementos de la vista con el controlador
        inicializarElementos();

        // TODO (HTTP): 2. Inicializar cola
        mRequestQueue = Volley.newRequestQueue(this);

        // TODO (HTTP): 3. Crear nueva solicitud
        stringRequest = new StringRequest(Request.Method.GET, DummyData.url(idCiudad), new Response.Listener<String>(){

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {
                Parser parser = new Parser(response.toString());

                clima = new Clima(
                        Float.parseFloat(parser.getValordeObjeto("main", "temp")),
                        parser.getValordeArreglo("weather", 0, "description"),
                        parser.getValordeCampo("name")
                );

                tvCiudad.setText(clima.getCiudad());
                tvDescripcion.setText(clima.getDescripcion());
                tvTemperatura.setText(String.valueOf(clima.getTemperaturaEnCelcius()) + "°");

                String bgName = clima.getDescripcion() + ".png";
                bgName = bgName.replace(" ","_");

               cambiarImagenFondo(bgName);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                tvDescripcion.setText("Hubo un error en la solicitud, vuelve a intentarlo");
            }
        });

        // TODO (HTTP): 4. Añadir solicitud a cola
        mRequestQueue.add(stringRequest);
    }




    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void cambiarImagenFondo(String name){
        try{
            InputStream stream = getAssets().open(name);
            Drawable draw = Drawable.createFromStream(stream, null);
            bg.setBackground(draw);
        }catch (Exception ioe){
            ioe.printStackTrace();
        }
    }

    private void inicializarElementos(){
        tvCiudad = (TextView)findViewById(R.id.tvCiudad);
        tvTemperatura = (TextView)findViewById(R.id.tvTemperatura);
        tvDescripcion = (TextView)findViewById(R.id.tvDescripcion);
        bg = (LinearLayout)findViewById(R.id.bg);
    }

}
