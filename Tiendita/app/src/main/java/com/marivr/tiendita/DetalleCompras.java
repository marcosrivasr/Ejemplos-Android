package com.marivr.tiendita;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.marivr.tiendita.Adaptadores.DetalleCompras.RecyclerViewClickListener;
import com.marivr.tiendita.Adaptadores.DetalleCompras.RecyclerViewCustomAdapter;
import com.marivr.tiendita.Datos.CompraRegistro;
import com.marivr.tiendita.Datos.DB.CompraCRUD;
import com.marivr.tiendita.Datos.DB.ComprasCRUD;
import com.marivr.tiendita.Util.Util;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.marivr.tiendita.R.id.collapsingToolbarLayout;

public class DetalleCompras extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    private RecyclerView lista;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewCustomAdapter adapter;

    private ArrayList<CompraRegistro> items;

    private TextView tvNoElementos;

    private CompraCRUD crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_compras);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        tvNoElementos = (TextView) findViewById(R.id.tvNoElementos);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int index = Integer.parseInt(getIntent().getStringExtra("com.marivr.tiendita.HistorialCompras.ID"));
        float total = Float.parseFloat(getIntent().getStringExtra("com.marivr.tiendita.HistorialCompras.TOTAL"));
        crud = new CompraCRUD(this);
        items = crud.leerRegistros(index);


        collapsingToolbarLayout.setTitle(Util.getDecimalFormat(total));



        lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);
        RecyclerViewCustomAdapter adapter  = new RecyclerViewCustomAdapter(items, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        lista.setAdapter(adapter);
        tvNoElementos.setText(items.size() + " elementos");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo, menu);
        return true;
    }

}
