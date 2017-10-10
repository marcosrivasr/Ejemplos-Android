package com.marivr.basededatos.Views;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.marivr.basededatos.Datos.Alumno;
import com.marivr.basededatos.Datos.AlumnoCRUD;
import com.marivr.basededatos.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Detalle extends AppCompatActivity {

    private AlumnoCRUD crud;
    private Alumno alumno;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView tvMatricula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        tvMatricula = (TextView) findViewById(R.id.tvMatricula);

        String id = getIntent().getStringExtra("com.marivr.basededatos.main.ID");

        crud = new AlumnoCRUD(this);
        alumno = crud.getAlumno(id);

        collapsingToolbarLayout.setTitle(alumno.getNombre());
        tvMatricula.setText(alumno.getId());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
