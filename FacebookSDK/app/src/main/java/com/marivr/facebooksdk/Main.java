package com.marivr.facebooksdk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static android.R.attr.data;
import com.facebook.appevents.AppEventsLogger;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;


public class Main extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView tvNombre;
    private CircleImageView ciFoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();

        tvNombre = (TextView) findViewById(R.id.tvNombre);

        ciFoto = (CircleImageView) findViewById(R.id.ciFoto);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "user_friends","public_profile"));

        //loginButton.setReadPermissions("email");

        if(AccessToken.getCurrentAccessToken() != null && Profile.getCurrentProfile() != null){
            tvNombre.setText("Bienvenido " + Profile.getCurrentProfile().getName());
            startActivity(new Intent(this, ListaAmigos.class));
            obtenerDatos();
        }

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                tvNombre.setText("Bienvenido " + Profile.getCurrentProfile().getName());
                Log.d("onSuccess", AccessToken.getCurrentAccessToken().getToken());
                Log.d("onSuccess", Profile.getCurrentProfile().getFirstName());
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void obtenerDatos() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me?fields=picture.height(300)",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        /* handle the result */
                        try{
                            //obtenemos URL de la imagen
                            String url = response.getJSONObject().getJSONObject("picture").getJSONObject("data").getString("url");
                            // obtenemos la foto de perfil
                            Picasso.with(Main.this).load(url).into(ciFoto);
                        }catch (Exception e){

                        }
                    }
                }
        ).executeAsync();
    }
}
