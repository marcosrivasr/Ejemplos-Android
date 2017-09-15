package com.marivr.materialdesign.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.marivr.materialdesign.Modelo.DummyData;
import com.marivr.materialdesign.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private ListView lista;
    private  ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(myToolbar);

        lista = (ListView) findViewById(R.id.lista);

        final ArrayList<String> ciudades = DummyData.obtenerCiudades();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ciudades);
        //rvAdapter = new CustomAdapter(this, ciudades);

        lista.setAdapter(adapter);
        
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, "Hola", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, TerceraPantalla.class));
            }
        });
    }
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.itemFavorito:
                startActivity(new Intent(MainActivity.this, SegundaPantalla.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
