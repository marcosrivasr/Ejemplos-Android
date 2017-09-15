package com.marivr.gridviewejemplo;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<Integer> imagenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagenes = new ArrayList<Integer>();
        imagenes.add(R.drawable.fondo01);
        imagenes.add(R.drawable.fondo02);
        imagenes.add(R.drawable.fondo03);
        imagenes.add(R.drawable.fondo04);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridViewAdapter(this, imagenes));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Elemento " + position, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
