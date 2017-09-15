package com.marivr.pruebaexamen;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Puesto> puestos = new ArrayList<Puesto>();
        puestos.add(new Puesto(1,"Pelos Tec","A un lado del Tec"));
        puestos.add(new Puesto(2,"Pelos Tollocan","En Tollocan"));
        puestos.add(new Puesto(3,"Pelos Las Torres","Sobre Las Torres"));
        Response response = new Response(
                new Ubicacion("12.222", "-7.999", "Toluca"),
                3,
                puestos);

        Gson gson = new Gson();
        String json = gson.toJson(response, Response.class);
        Log.d("json", json);
    }
}

