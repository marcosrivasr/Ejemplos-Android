package com.marivr.tiendita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.marivr.tiendita.Adaptadores.HistorialCompras.RecyclerViewClickListener;
import com.marivr.tiendita.Adaptadores.HistorialCompras.RecyclerViewCustomAdapter;
import com.marivr.tiendita.Datos.ComprasRegistro;
import com.marivr.tiendita.Datos.DB.ComprasCRUD;
import com.marivr.tiendita.Datos.DB.ProductoCRUD;
import com.marivr.tiendita.Datos.Producto;

import java.util.ArrayList;

public class HistorialCompras extends AppCompatActivity {

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

    private ComprasCRUD crud;

    private ArrayList<ComprasRegistro> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_compras);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        crud = new ComprasCRUD(this);
        items = crud.leerRegistros();

        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);
        RecyclerViewCustomAdapter adapter  = new RecyclerViewCustomAdapter(items, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(HistorialCompras.this, DetalleCompras.class);
                intent.putExtra("com.marivr.tiendita.HistorialCompras.ID", String.valueOf(items.get(position).getId()));
                intent.putExtra("com.marivr.tiendita.HistorialCompras.TOTAL", String.valueOf(items.get(position).getTotal()));
                startActivity(intent);
            }
        });
        lista.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo, menu);
        return true;
    }

}
