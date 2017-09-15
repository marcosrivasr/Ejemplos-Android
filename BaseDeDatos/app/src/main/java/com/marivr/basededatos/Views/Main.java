package com.marivr.basededatos.Views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marivr.basededatos.CustomAdapter;
import com.marivr.basededatos.Datos.Alumno;
import com.marivr.basededatos.Datos.AlumnosContract;
import com.marivr.basededatos.Datos.DataBaseHelper;
import com.marivr.basededatos.R;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    private ListView lista;
    private ArrayList<Alumno> alumnos;
    private FloatingActionButton fab;

    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView)findViewById(R.id.lista);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        alumnos = new ArrayList<Alumno>();
        helper = new DataBaseHelper(this);

        readFromDB();

        ArrayAdapter<Alumno> adapter = new CustomAdapter(this, alumnos);
        lista.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main.this, Nuevo.class));
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(Main.this, Detalle.class));
            }
        });
    }

    public void readFromDB(){
        SQLiteDatabase db = helper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AlumnosContract.Entrada.COLUMNA_ID,
                AlumnosContract.Entrada.COLUMNA_NOMBRE,
                AlumnosContract.Entrada.COLUMNA_AVATAR
        };

        Cursor c = db.query(
                AlumnosContract.Entrada.NOMBRE_TABLA,
                projection,
                null,
                null,
                null,
                null,
                null);

            while (c.moveToNext()){
                alumnos.add(new Alumno(
                        c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_ID)),
                        c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_NOMBRE)),
                        c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_AVATAR))
                ));
            }
    }

}
