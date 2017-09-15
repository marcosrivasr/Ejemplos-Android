package com.marivr.materialdesign.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.marivr.materialdesign.Customs.RecycleViewCustomAdapter;
import com.marivr.materialdesign.Modelo.DummyData;
import com.marivr.materialdesign.R;

public class SegundaPantalla extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pantalla);


        Toolbar myChildToolbar =
        (Toolbar) findViewById(R.id.segundaToolbar);
        setSupportActionBar(myChildToolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.reciclerView);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecycleViewCustomAdapter adapter  = new RecycleViewCustomAdapter(DummyData.obtenerUsuarios());
        recyclerView.setAdapter(adapter);

    }
}
