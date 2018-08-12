package com.marivr.tiendita;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.marivr.tiendita.Adaptadores.Productos.RecyclerViewClickListener;
import com.marivr.tiendita.Datos.Producto;
import com.marivr.tiendita.Datos.DB.ProductoCRUD;
import com.marivr.tiendita.Adaptadores.Productos.RecyclerViewCustomAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main extends AppCompatActivity {

    private FloatingActionButton fab;
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

    private ProductoCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Main.this, Compras.class));

            }
        });


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        crud = new ProductoCRUD(this);
        final ArrayList<Producto> productos = crud.leerProductos();

        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);
        RecyclerViewCustomAdapter adapter  = new RecyclerViewCustomAdapter(productos, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Producto item = productos.get(position);
                Intent intent = new Intent(Main.this, DetalleProducto.class);
                intent.putExtra("com.marivr.tiendita.main.ID",String.valueOf(item.getId()));
                intent.putExtra("com.marivr.tiendita.main.NOMBRE",item.getNombre());
                intent.putExtra("com.marivr.tiendita.main.PRECIO", String.valueOf(item.getPrecio()));
                intent.putExtra("com.marivr.tiendita.main.FOTO", item.getFoto());
                startActivity(intent);
            }
        });
        lista.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemNuevaCompra:
                startActivity(new Intent(Main.this, Nuevo.class));
                return true;
            case R.id.itemVerHistorial:
                startActivity(new Intent(this, HistorialCompras.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
