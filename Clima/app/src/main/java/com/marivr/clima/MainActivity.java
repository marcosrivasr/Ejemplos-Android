package com.marivr.clima;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marivr.clima.Datos.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvCiudades;
    public final static String MENSAJE_EXTRA= "com.marivr.clima.MENSAJE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Se asocia la variable con el elemento
        lvCiudades = (ListView)findViewById(R.id.lvCiudades);

        // Rellenar la lista con datos
        ArrayList<Ciudad> ciudades = DummyData.getCiudades();

        // Se crea un objeto Adapter que contendrá la lista de ciudades
        //ArrayAdapter<Ciudad> adaptador = new ArrayAdapter<Ciudad>(this, android.R.layout.simple_list_item_1, ciudades);
        ArrayAdapter<Ciudad> adaptador = new CustomAdapter(this, ciudades);

        // Se hace el binding de la vista con los datos
        lvCiudades.setAdapter(adaptador);

        // Se añade listener para un evento click
        lvCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Se obtiene el objeto de tipo Ciudad de acuerdo al elemento que se hizo clic
                Ciudad ciudad = (Ciudad) parent.getItemAtPosition(position);
                String idCiudad =  String.valueOf(ciudad.getId());

                // Se muestra el ID
                //Toast.makeText(getApplicationContext(), idCiudad, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ClimaView.class);
                intent.putExtra(MENSAJE_EXTRA, idCiudad);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
