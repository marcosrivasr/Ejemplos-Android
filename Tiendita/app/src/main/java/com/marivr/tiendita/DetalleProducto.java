package com.marivr.tiendita;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.marivr.tiendita.Datos.DB.ProductoCRUD;
import com.marivr.tiendita.Datos.Producto;
import com.marivr.tiendita.Util.Util;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetalleProducto extends AppCompatActivity {

    private TextView tvNombre, tvPrecio;
    private Button bEliminar;
    private FloatingActionButton fab;

    private String id;

    private ProductoCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        id = getIntent().getStringExtra("com.marivr.tiendita.main.ID");
        String nombre = getIntent().getStringExtra("com.marivr.tiendita.main.NOMBRE");
        String precio = getIntent().getStringExtra("com.marivr.tiendita.main.PRECIO");
        String foto = getIntent().getStringExtra("com.marivr.tiendita.main.FOTO");

        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        bEliminar = (Button) findViewById(R.id.bEliminar);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvNombre.setText(nombre);
        tvPrecio.setText(Util.getDecimalFormat(Float.parseFloat(precio)));


        crud = new ProductoCRUD(this);


        bEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crud.deleteProducto(Integer.parseInt(id));
                Toast.makeText(DetalleProducto.this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DetalleProducto.this, Main.class));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleProducto.this, EditProduct.class);
                intent.putExtra("com.marivr.tiendita.detalleProducto.TYPE", "edit");
                intent.putExtra("com.marivr.tiendita.detalleProducto.ID", id);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nuevo, menu);
        return true;
    }

}
