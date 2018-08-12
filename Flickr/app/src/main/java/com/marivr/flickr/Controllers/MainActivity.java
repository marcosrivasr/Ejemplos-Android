package com.marivr.flickr.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marivr.flickr.Customs.CustomImageAdapter;
import com.marivr.flickr.Datos.DummyData;
import com.marivr.flickr.Datos.Foto;
import com.marivr.flickr.R;
import com.marivr.flickr.Utilities.CustomParser;

import org.json.JSONArray;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;

    private ImageView iv;
    private ArrayList<Foto> listaFotos;
    private GridView gvImagenes;

    //private ProgressBar progress;

    private void inicializarComponentes(){

        //iv = (ImageView) findViewById(R.id.imageView);
        gvImagenes = (GridView) findViewById(R.id.gvImagenes);

        listaFotos = new ArrayList<Foto>();



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();



        // Se inicia una nueva petición
        mRequestQueue = Volley.newRequestQueue(this);

        peticionListaImagenes();
    }

    private void peticionListaImagenes(){
        // TODO (1): Crear peticion
        stringRequest = new StringRequest(Request.Method.GET, DummyData.url(getSearch()), new Response.Listener<String>(){

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(String response) {
                CustomParser parser = new CustomParser(response.toString());
                JSONArray fotos = parser.getArregloDeObjeto("photos", "photo");

                for(int i = 0; i<parser.getArregloDeObjeto("photos", "photo").length(); i++){
                    listaFotos.add(new Foto(
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "id"), //fotos.getJSONObject(i).getString("id"),
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "owner"), //fotos.getJSONObject(i).getString("owner"),
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "secret"), //fotos.getJSONObject(i).getString("secret"),
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "server"), //fotos.getJSONObject(i).getString("server"),
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "farm"), //fotos.getJSONObject(i).getString("farm"),
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "title"), //fotos.getJSONObject(i).getString("title"),
                            parser.getValorDeObjetoN2DeArreglo("photos", "photo", i, "ispublic"))); //fotos.getJSONObject(i).getString("ispublic")));

                    cargarImagen(i);

                    //progress.dismiss();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getMessage());
                Toast.makeText(MainActivity.this, "Error en la solicitud. Revisa tu conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(stringRequest);
    }

    void cargarImagen(int index){
        String url = listaFotos.get(index).getImagenURL();
        final int i = index;


        ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                Drawable d = new BitmapDrawable(getResources(), response);

                listaFotos.get(i).setImagen(d);

                gvImagenes.setAdapter(new CustomImageAdapter(MainActivity.this, listaFotos));

                gvImagenes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Foto item = listaFotos.get(position);
                        Intent i = new Intent(MainActivity.this, IndividualImage.class);
                        i.putExtra("myDataKey", item); // using the (String name, Parcelable value) overload!
                        startActivity(i); // dataToSend is now passed to the new Activity
                    }
                });
            }
        }, 200, 200, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error en la solicitud. Revisa tu conexión a internet", Toast.LENGTH_SHORT).show();
                Log.e("com.marivr.flickr", error.toString());
            }
        });
        mRequestQueue.add(ir);
    }

    private String getSearch(){
        Intent intent = getIntent();
        return intent.getStringExtra(Sections.TAG);
    }

}
