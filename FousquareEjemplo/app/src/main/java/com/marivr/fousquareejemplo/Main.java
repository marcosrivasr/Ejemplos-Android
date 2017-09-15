package com.marivr.fousquareejemplo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.foursquare.android.nativeoauth.FoursquareCancelException;
import com.foursquare.android.nativeoauth.FoursquareDenyException;
import com.foursquare.android.nativeoauth.FoursquareInvalidRequestException;
import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.FoursquareOAuthException;
import com.foursquare.android.nativeoauth.FoursquareUnsupportedVersionException;
import com.foursquare.android.nativeoauth.model.AccessTokenResponse;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;

public class Main extends AppCompatActivity {

    // TODO: 1.- Creación de variables para validar resultado de solicitud de conexión y de intercambio de tokens
    private static final int REQUEST_CODE_FSQ_CONNECT = 200; // Código de conexión exitosa
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201; // Código de intercambio de token exitosa
    private static final String CLIENT_ID = "WUNP0C0YRJ50ELKZNIZXWKQ02YXKUXXIQYTT24VRI3DBHDFT"; // clave ID
    private static final String CLIENT_SECRET = "C1SRLK1PCDR4QU1BIZ3RBOS5FW1550UIYJ22N25KKZTBNTSB"; // clave secreta

    // TODO: 2.- boton para loguear
    private Button bLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: 3.- Linkeamos botón con
        bLogin = (Button)findViewById(R.id.bLogin);

        manageLoginUI();
    }

    private void manageLoginUI() {
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 4.- Inicio de un nuevo flujo a través de un intent
                Intent intent = FoursquareOAuth.getConnectIntent(Main.this, CLIENT_ID);

                // TODO: 5.-
                // Si el dispositivo no tiene instalada la app de Fousquare se le va a pedir que
                // la instale, usando un intent hacia la Play Store
                if(FoursquareOAuth.isPlayStoreIntent(intent)){
                    Toast.makeText(Main.this, "La app no está instalada", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    // Si está la app instalada empieza el proceso de autenticación
                    startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_FSQ_CONNECT: // Se pudo hacer la conexión
                onCompleteConnect(resultCode, data);
                break;
            case REQUEST_CODE_FSQ_TOKEN_EXCHANGE: // Ya se dio el permiso, hace falta obtener el token
                onCompleteTokenExchange(resultCode, data);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onCompleteConnect(int resultCode, Intent data) {
        // TODO: 3.- Se valida que no haya excepción de conexión
        AuthCodeResponse codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data);
        Exception exception = codeResponse.getException();

        if (exception == null) {
            // Si no hay excepción se obtiene el código para cambiarlo por un token
            // y se llama la función para hacer el intercambio
            String code = codeResponse.getCode();
            performTokenExchange(code);
        } else {
            if (exception instanceof FoursquareCancelException) {/* Cancel */
            } else if (exception instanceof FoursquareDenyException) {/* Deny.*/
            } else if (exception instanceof FoursquareOAuthException) {
                // OAuth error.
                String errorMessage = exception.getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
            } else if (exception instanceof FoursquareUnsupportedVersionException) {/* Unsupported Fourquare app version on the device. */
            } else if (exception instanceof FoursquareInvalidRequestException) {/* Invalid request. */
            } else {/*Error.*/}
        }
    }


    private void onCompleteTokenExchange(int resultCode, Intent data) {
        // Se lleva a cabo la solicitud de token
        AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);
        Exception exception = tokenResponse.getException();

        // Si no hay ninguna excepción o problema quiere decir que ya se puede obtener el token
        // de acceso
        if (exception == null) {
            String accessToken = tokenResponse.getAccessToken();

            // Guardamos el token para usarlo posteriormentdx {ñ.
            Toast.makeText(this, "Token " + accessToken, Toast.LENGTH_SHORT).show();
            // Actualizamos la interfaz
            manageLoginUI();
        } else {
            if (exception instanceof FoursquareOAuthException) {
                // Error en el proceso de autenticación OAuth
                String errorMessage = ((FoursquareOAuthException) exception).getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
            } else {
                // Excepciones
            }
        }
    }


    private void performTokenExchange(String code) {
        Intent intent = FoursquareOAuth.getTokenExchangeIntent(this, CLIENT_ID, CLIENT_SECRET, code);
        startActivityForResult(intent, REQUEST_CODE_FSQ_TOKEN_EXCHANGE);
    }

}
