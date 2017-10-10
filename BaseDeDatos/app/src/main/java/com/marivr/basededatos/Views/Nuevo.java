package com.marivr.basededatos.Views;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marivr.basededatos.Datos.Alumno;
import com.marivr.basededatos.Datos.AlumnoCRUD;
import com.marivr.basededatos.Datos.AlumnosContract;
import com.marivr.basededatos.Datos.DataBaseHelper;
import com.marivr.basededatos.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Nuevo extends AppCompatActivity {

    private EditText tvId, tvNombre;
    private Button bAdd;

    private AlumnoCRUD crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        tvId = (EditText) findViewById(R.id.tvMatricula);
        tvNombre = (EditText) findViewById(R.id.tvNombre);

        bAdd = (Button) findViewById(R.id.bAdd);

        crud = new AlumnoCRUD(this);

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.newAlumno(new Alumno(tvId.getText().toString(), tvNombre.getText().toString()));
                startActivity(new Intent(Nuevo.this, Main.class));
            }
        });
    }
}
