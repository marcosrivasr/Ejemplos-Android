package com.marivr.flickr.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marivr.flickr.Customs.CustomListViewAdapter;
import com.marivr.flickr.Datos.Categoria;
import com.marivr.flickr.Datos.DummyData;
import com.marivr.flickr.R;

import java.util.ArrayList;

public class Sections extends AppCompatActivity {

    private ListView lvCategorias;
    public static final String TAG = "com.marivr.flickr.CATEGORIA";
    public static final String TAG_LOG = "Sections";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);


        Log.i(TAG_LOG, "Va a cargarse la info, atentos!");
        // TODO (1): Asociar elemento
        lvCategorias = (ListView) findViewById(R.id.lvCategorias);

        // TODO (2): Rellenar la lista con datos
        ArrayList<Categoria> categorias = DummyData.getCategorias();

        // TODO (3): Crear y enlazar adapter
        ArrayAdapter<Categoria> adapter = new CustomListViewAdapter(this, categorias);
        lvCategorias.setAdapter(adapter);

        // TODO (4): Crear listener
        lvCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categoria item = (Categoria) parent.getItemAtPosition(position);

                // TODO (5): Insertar Intent
                Intent intent = new Intent(Sections.this, MainActivity.class);
                intent.putExtra(TAG, item.getId());
                startActivity(intent);
            }
        });

    }
}
