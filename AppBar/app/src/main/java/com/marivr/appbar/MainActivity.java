package com.marivr.appbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ShareActionProvider mShareActionProvider;
    private Intent sharingIntent;

    //private RecyclerView lista;
    private ListView lista;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;
    private  ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*lista = (RecyclerView) findViewById(R.id.lista);
        lista.setHasFixedSize(true);
        rvLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(rvLayoutManager);
*/
        lista = (ListView) findViewById(R.id.lista);

        final ArrayList<String> ciudades = new ArrayList<String>();
        ciudades.add("Mexico");
        ciudades.add("Chicago");
        ciudades.add("Barcelona");
        ciudades.add("Paris");
        ciudades.add("Moscú");
        ciudades.add("Seattle");
        ciudades.add("Dallas");
        ciudades.add("Buenos Aires");
        ciudades.add("Lima");
        ciudades.add("Lisboa");
        ciudades.add("Atenas");
        ciudades.add("Shangai");
        ciudades.add("Korea");
        ciudades.add("Sudáfrica");
        ciudades.add("Montevideo");
        ciudades.add("Cartagena");
        ciudades.add("Barranquilla");

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, ciudades);
        //rvAdapter = new CustomAdapter(this, ciudades);

        lista.setAdapter(adapter);
        //lista.setAdapter(rvAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);

                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

       /* MenuItem item = (MenuItem)menu.findItem(R.id.itemCompartir);

        sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.anilinkz.com/");
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        setShareIntent(sharingIntent);*/

        MenuItem itemBuscar = (MenuItem) menu.findItem(R.id.itemBuscar);
        SearchView searchView = (SearchView) itemBuscar.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemFav:
                startActivity(new Intent(this, Main2Activity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Create and return the Share Intent
    private Intent createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://droiddevguide.blogspot.ae/");
        return shareIntent;
    }

    public void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
    // Sets new share Intent.
    // Use this method to change or set Share Intent in your Activity Lifecycle.
    private void changeShareIntent(Intent shareIntent) {
        mShareActionProvider.setShareIntent(shareIntent);
    }

}
