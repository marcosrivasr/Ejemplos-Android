package com.marivr.facebooksdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;
import com.marivr.facebooksdk.Customs.RecycleViewCustomAdapter;
import com.marivr.facebooksdk.Customs.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

public class ListaAmigos extends AppCompatActivity {
    private CallbackManager callbackManager;
    private ResponseFriends responseFriends;


    private RecyclerView lista;
    private LinearLayoutManager linearLayoutManager;
    private RecycleViewCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amigos);

        lista = (RecyclerView) findViewById(R.id.lista);
        linearLayoutManager = new LinearLayoutManager(this);
        lista.setLayoutManager(linearLayoutManager);

        callbackManager = CallbackManager.Factory.create();
        obtenerDatos();
    }

    public void obtenerDatos() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/taggable_friends?fields=id,picture.width(200),name",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        try{
                            Log.d("sa2", response.toString());
                            Gson gson = new Gson();
                            responseFriends = gson.fromJson(response.getRawResponse(), ResponseFriends.class);
                            adapter  = new RecycleViewCustomAdapter(responseFriends.getAmigos(), new RecyclerViewClickListener() {
                                @Override
                                public void onClick(View view, int position) {

                                }
                            },ListaAmigos.this);
                            lista.setAdapter(adapter);

                        }catch (Exception e){

                        }
                    }
                }
        ).executeAsync();
    }
}
