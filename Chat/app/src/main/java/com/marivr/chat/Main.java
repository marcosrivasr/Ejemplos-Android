package com.marivr.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Random;

public class Main extends AppCompatActivity {
    ArrayAdapter adapter;
    ListView lista;
    Button boton;
    EditText campo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lista);
        boton = (Button) findViewById(R.id.boton);
        campo = (EditText) findViewById(R.id.campo);
        adapter = new CustomAdapter(this, R.layout.activity_fila_custom);
        lista.setAdapter(adapter);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                adapter.add(new Mensaje(r.nextBoolean(), campo.getText().toString()));

                campo.setText("");
            }
        });

    }
}
