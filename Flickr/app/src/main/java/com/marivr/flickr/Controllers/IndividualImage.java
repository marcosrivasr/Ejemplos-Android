package com.marivr.flickr.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marivr.flickr.Datos.DummyData;
import com.marivr.flickr.Datos.Foto;
import com.marivr.flickr.Datos.FotoDetalle;
import com.marivr.flickr.R;
import com.marivr.flickr.Utilities.CustomParser;

import org.json.JSONArray;

import static com.marivr.flickr.R.id.gvImagenes;
import static com.marivr.flickr.R.id.ivIcono;

public class IndividualImage extends AppCompatActivity {

    private TextView tvTitulo;
    private TextView tvTituloH;
    private TextView tvAutor;
    private TextView tvDescripcion;
    private TextView tvDescripcionH;
    private TextView tvUserName;
    private ImageView ivFoto;
    private ImageView ivIcono;
    private RequestQueue mRequestQueue;
    private FotoDetalle fotoDetalle;
    private  Foto foto;

    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_image);

        foto = (Foto) getIntent().getParcelableExtra("myDataKey");
        Log.i("individualImage", foto.getTitle());

        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvAutor = (TextView) findViewById(R.id.tvAutor);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);
        ivIcono = (ImageView) findViewById(R.id.ivIcono);


        tvTitulo.setText(foto.getTitle());

        mRequestQueue = Volley.newRequestQueue(this);

        //progress = ProgressDialog.show(this, "Cargando...", "Descargando información", true);

        cargarImagen(foto.getImagenURL());

        cargarInfoUsuario(DummyData.detalleURL(foto.getId(), foto.getSecret()));

    }

    void cargarImagen(String url){
        ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //iv.setImageBitmap(response);
                Drawable d = new BitmapDrawable(getResources(), response);

                ivFoto.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("com.marivr.flickr", error.toString());
            }
        });
        mRequestQueue.add(ir);
    }
    void cargarImagenPerfil(String url){
        ImageRequest ir = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                //iv.setImageBitmap(response);
                Drawable d = new BitmapDrawable(getResources(), response);

                ivIcono.setImageBitmap(response);
            }
        }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("com.marivr.flickr", error.toString());
            }
        });
        mRequestQueue.add(ir);
    }

    void cargarInfoUsuario(String url){
        StringRequest ir = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                CustomParser parser = new CustomParser(response.toString());

                fotoDetalle = new FotoDetalle(
                        foto.getId(),
                        foto.getOwner(),
                        foto.getSecret(),
                        foto.getServer(),
                        foto.getFarm(),
                        foto.getTitle(),
                        foto.getIsPublic(),
                        parser.getValorDeObjetoN2("photo", "owner", "username"),
                        parser.getValorDeObjetoN2("photo", "owner", "realname"),
                        parser.getValorDeObjetoN2("photo", "owner", "iconserver"),
                        parser.getValorDeObjetoN2("photo", "owner", "nsid"),
                        parser.getValorDeObjetoN2("photo", "owner", "iconfarm"),
                        parser.getValorDeObjetoN2("photo","description", "_content"));

                tvAutor.setText(fotoDetalle.getRealName());
                tvDescripcion.setText(fotoDetalle.getDescription());
                tvUserName.setText(fotoDetalle.getUserName());
                cargarImagenPerfil(fotoDetalle.getFotoPerfil());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.getMessage());
            }
        });
        mRequestQueue.add(ir);
    }



}
