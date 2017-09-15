package com.marivr.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvFrutas = (ListView) findViewById(R.id.lvFrutas);

        ArrayList<String> listaDeFrutas = new ArrayList<String>();
        listaDeFrutas.add("Manzana");
        listaDeFrutas.add("Durazno");
        listaDeFrutas.add("Naranja");

        //String [] listaDeFrutas = new String[]{"Manzana", "Durazno"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listaDeFrutas);

        lvFrutas.setAdapter(adapter);

        lvFrutas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fruta = (String) parent.getItemAtPosition(position);
                // CÓDIGO...
            }
        });
    }
}
