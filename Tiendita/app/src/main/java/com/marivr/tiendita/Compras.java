package com.marivr.tiendita;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.marivr.tiendita.Adaptadores.Compras.RecyclerViewClickListener;
import com.marivr.tiendita.Adaptadores.Compras.RecyclerViewCustomAdapter;
import com.marivr.tiendita.Datos.Compra;
import com.marivr.tiendita.Datos.CompraRegistro;
import com.marivr.tiendita.Datos.ComprasRegistro;
import com.marivr.tiendita.Datos.DB.CompraCRUD;
import com.marivr.tiendita.Datos.DB.ComprasCRUD;
import com.marivr.tiendita.Datos.Producto;
import com.marivr.tiendita.Datos.DB.ProductoCRUD;
import com.marivr.tiendita.Util.Util;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class Compras extends AppCompatActivity {

    private Spinner spinner;
    private Button bAdd;
    private TextView tvNoElementos;
    private EditText etCantidad;
    private FloatingActionButton fab;

    private CollapsingToolbarLayout collapsingToolbarLayout;

    private ProductoCRUD crud;
    private ComprasCRUD comprasCRUD;
    private CompraCRUD compraCRUD;

    private ArrayList<String> listaNombreProductos;
    private ArrayList<Producto> productos;
    private ArrayList<Compra> listaCompras;

    /*
     * Objeto UI para mostrar lista de lugares
     */
    private RecyclerView lista;

    /*
     * Objeto para administrar la forma en que se ven los
     * elementos en un RecyclerView
     */
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewCustomAdapter adapterCompras;

    private float costoTotal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        iniciarUI();

        crud = new ProductoCRUD(this);
        comprasCRUD = new ComprasCRUD(this);
        compraCRUD = new CompraCRUD(this);


        listaNombreProductos = crud.leerNombreProductos();
        productos = crud.leerProductos();
        listaCompras = new ArrayList<Compra>();

        configurarSpinner();

        configurarRecyclerView();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = spinner.getSelectedItemPosition();
                int cantidad = Integer.parseInt(etCantidad.getText().toString());
                listaCompras.add(new Compra(productos.get(i), cantidad));
                adapterCompras.notifyDataSetChanged();

                actualizarPrecios();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 19.2622782,-99.6447954
                ComprasRegistro newItem = new ComprasRegistro("Compra " + Util.now(), "19.2622782","-99.6447954", Util.now(), listaCompras.size(), costoTotal);
                comprasCRUD.crearNuevoElemento(newItem);

                ArrayList<ComprasRegistro> x = comprasCRUD.leerRegistros();

                int id_compras = comprasCRUD.getReciente().getId();
                for (Compra c: listaCompras) {
                    compraCRUD.crearNuevoElemento(new CompraRegistro(
                            c.getProducto().getNombre(),
                            c.getCantidad(),
                            c.getProducto().getPrecio(),
                            id_compras));
                }
                Snackbar.make(collapsingToolbarLayout, "Nueva compra guardada!", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void configurarRecyclerView() {
        lista.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);
        adapterCompras  = new RecyclerViewCustomAdapter(listaCompras, this, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        lista.setAdapter(adapterCompras);
    }

    private void configurarSpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, listaNombreProductos);

        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo, menu);
        return true;
    }

    private void iniciarUI(){
        spinner = (Spinner) findViewById(R.id.spinner);
        bAdd = (Button) findViewById(R.id.bAdd);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        lista = (RecyclerView) findViewById(R.id.lista);
        tvNoElementos = (TextView) findViewById(R.id.tvNoElementos);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void actualizarPrecios(){
        if(listaCompras.size() == 0){
            tvNoElementos.setText("0 elementos");
            collapsingToolbarLayout.setTitle("$0.0");
        }else{
            tvNoElementos.setText(String.valueOf(listaCompras.size()) + " elementos");
            costoTotal = 0;
            for (Compra c: listaCompras) {
                costoTotal += c.getTotal();
            }
            collapsingToolbarLayout.setTitle(new DecimalFormat("$##,##0.00").format(costoTotal));
        }

    }

}
