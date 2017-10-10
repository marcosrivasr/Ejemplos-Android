package com.marivr.basededatos.Views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marivr.basededatos.Adaptadores.RecyclerViewClickListener;
import com.marivr.basededatos.Adaptadores.RecyclerViewCustomAdapter;
import com.marivr.basededatos.Datos.Alumno;
import com.marivr.basededatos.Datos.AlumnoCRUD;
import com.marivr.basededatos.Datos.AlumnosContract;
import com.marivr.basededatos.Datos.DataBaseHelper;
import com.marivr.basededatos.R;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private ArrayList<Alumno> alumnos;
    private FloatingActionButton fab;

    private RecyclerView lista;
    private LinearLayoutManager linearLayoutManager;
    private AlumnoCRUD crud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fab = (FloatingActionButton) findViewById(R.id.fab);

        alumnos = new ArrayList<Alumno>();

        crud = new AlumnoCRUD(this);
        alumnos = crud.getAlumnos();

        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);
        RecyclerViewCustomAdapter adapter  = new RecyclerViewCustomAdapter(alumnos, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(Main.this, Detalle.class);
                intent.putExtra("com.marivr.basededatos.main.ID", alumnos.get(position).getId());
                startActivity(intent);
            }
        });
        lista.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, Nuevo.class));
            }
        });

    }


}
