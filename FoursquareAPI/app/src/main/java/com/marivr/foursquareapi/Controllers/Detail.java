package com.marivr.foursquareapi.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.marivr.foursquareapi.Customs.CustomAdapterPeople;
import com.marivr.foursquareapi.Model.Photos.Photo;
import com.marivr.foursquareapi.Model.Photos.PhotosResponse;
import com.marivr.foursquareapi.Model.Venues.Venue;
import com.marivr.foursquareapi.Networking.HttpRequest;
import com.marivr.foursquareapi.Networking.RequestBuilder;
import com.marivr.foursquareapi.R;
import com.marivr.foursquareapi.Util.Preferences;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import okhttp3.OkHttpClient;

public class Detail extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Venue item;
    private PhotosResponse photos;
    private OkHttpClient client;
    private Preferences p;

    private TextView tvName, tvCategory, tvCity, tvNumberChecks;
    private ImageView ivMain;
    private ListView lvPeople;

    private FloatingActionButton fab;

    private ShareActionProvider shareActionProvider;

    private Intent sharingIntent;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolbarLayout);
        tvName = (TextView) findViewById(R.id.tvName);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        tvCity = (TextView) findViewById(R.id.tvCity);
        ivMain = (ImageView) findViewById(R.id.ivMain);
        lvPeople = (ListView) findViewById(R.id.lvPeople);
        tvNumberChecks = (TextView) findViewById(R.id.tvNumberChecks);
        fab = (FloatingActionButton)findViewById(R.id.fab);



        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);



        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Gson gson = new Gson();
        item = gson.fromJson(intent.getStringExtra(SearchResults.ITEM), Venue.class);
        collapsingToolbarLayout.setTitle(item.getName());



        tvName.setText(valueOf(item.getName()));
        tvNumberChecks.setText(valueOf(item.getStats().getCheckinsCount() + " personas han estado aquí"));
        tvCategory.setText(valueOf(item.getCategories().get(0).getName()));
        if(item.getLocation().getAddress() != null){
            tvCity.setText(valueOf(item.getLocation().getAddress()) + " " +
                            valueOf(item.getLocation().getCity()) + ", " +
                            valueOf(item.getLocation().getCountry()));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Estoy en " + item.getName());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Compartir lugar..."));
            }
        });

        client = new OkHttpClient();
        p = new Preferences(this);

        new AsyncTask<Void, Void, Void>(){
            String response;

            @Override
            protected Void doInBackground(Void... params) {
                response = HttpRequest.GET(client, RequestBuilder.buildURLPhotos(item.getId(), p.getToken()));
                Log.d("loadContent", response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.d("onPostExecute", "hola");
                Gson gson = new Gson();
                photos = gson.fromJson(response, PhotosResponse.class);
                if(photos.getResponse().getPhotos().getItems().size() == 0){

                }else{
                    Photo photo = photos.getResponse().getPhotos().getItems().get(0);

                    if(photo != null) {
                        Picasso.with(Detail.this).load(photo.getPrefix() + "300x500" + photo.getSufix()).into(ivMain);
                    }
                    ArrayAdapter<Photo> adapter = new CustomAdapterPeople(Detail.this, photos.getResponse().getPhotos().getItems());
                    lvPeople.setAdapter(adapter);
                }
            }

            @Override
            protected void onPreExecute() {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(Detail.this);
                    progressDialog.setMessage(getString(R.string.loading_message));
                    progressDialog.show();
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.setCancelable(false);
                }

            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu_main, menu);

    // Locate MenuItem with ShareActionProvider
    MenuItem itemShare = menu.findItem(R.id.iShare);

    // Fetch and store ShareActionProvider
    sharingIntent = new Intent(Intent.ACTION_SEND);
    sharingIntent.setType("text/plain");
    sharingIntent.putExtra(Intent.EXTRA_TEXT, "Texto para compartir");
    shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(itemShare);
    shareActionProvider.setShareIntent(sharingIntent);
    // Return true to display menu
    return true;
    }

    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}
