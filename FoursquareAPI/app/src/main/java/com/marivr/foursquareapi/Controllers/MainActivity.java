package com.marivr.foursquareapi.Controllers;

import android.content.Context;
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
import com.marivr.foursquareapi.Networking.RequestBuilder;
import com.marivr.foursquareapi.R;
import com.marivr.foursquareapi.Util.Preferences;

public class MainActivity extends AppCompatActivity {

    /**
     * Variables necesarias para poder conectarse a la API de Foursquare
     */
    private static final int REQUEST_CODE_FSQ_CONNECT = 200; // Código de conexión exitosa
    private static final int REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 201; // Código de intercambio de token exitosa
    private static final String CLIENT_ID = "WUNP0C0YRJ50ELKZNIZXWKQ02YXKUXXIQYTT24VRI3DBHDFT"; // clave ID
    private static final String CLIENT_SECRET = "C1SRLK1PCDR4QU1BIZ3RBOS5FW1550UIYJ22N25KKZTBNTSB"; // clave secreta


    private Button bLogin;
    /**
     * Objeto de preferencias usado para poder almacenar el token de autenticación
     */
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se asocia el botón para iniciar sesión
        bLogin = (Button) findViewById(R.id.bLogin);


        // Se crea un nuevo objeto de Preferencias
        preferences = new Preferences(this);


        // Se manda a llamar función para administrar botones e interfaz
        manageLoginUI();
    }

    /**
     * Se asegura de mostrar la interfaz adecuada.
     * Si es la primera vez que el usuario se va a autenticar
     * es recomendable que aparezca la pantalla de login,
     * sino se salta a través de un intent a la página de búsqueda
     */
    private void manageLoginUI() {
        // Valida si existe un token guardado previamente,
        // Si es así se manda a llamar al intent para la siguiente actividad
        if(preferences.hayToken()){
            if(RequestBuilder.isInternetAvailable(this)){
                startActivity(new Intent(this, ValidLocation.class));
                finish();
            }else{
                toastMessage(this, getString(R.string.internet_error));
            }
        }

        // Si no hay token entonces se muestra la pantalla para que el usuario se autentique
        // Las acciones van a ser que una vez que se le presione al botón va a iniciar el proceso de
        // autenticación
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RequestBuilder.isInternetAvailable(MainActivity.this)){

                    // TODO: 1.- Inicio de un nuevo flujo a través de un intent
                    Intent intent = FoursquareOAuth.getConnectIntent(MainActivity.this, CLIENT_ID);

                    // Si el dispositivo no tiene instalada la app de Fousquare se le va a pedir que
                    // la instale, usando un intent hacia la Play Store
                    if (FoursquareOAuth.isPlayStoreIntent(intent)) {
                        toastMessage(MainActivity.this, "La app no está instalada");
                        startActivity(intent);
                    } else {
                        // Si está la app instalada empieza el proceso de autenticación
                        startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT);
                    }
                }else{
                    toastMessage(MainActivity.this, "Necesitas conexión a internet para seguir");
                }
            }

        });
    }

    /**
     * Cuando la solicitud de autenticación se completa se manda a llamar este método
     * para validar el objeto respuesta que se obtuvo
     *
     * @param requestCode Código que se envió para la solicitud
     * @param resultCode Código de respuesta de la solicitud
     * @param data Intent que está activo en el flujo de autenticación
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO 2.- Se valida el tipo de respuesta de la comunicación
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

    /**
     * Ahora que se completó la conexión se validar el tipo de mensaje que genera
     * Si no se genera ninguna excepción se procede al intercambio de la información de autenticación
     * para generar el token de acceso.
     * Si existe alguna excepción se mapea y se ejecuta de acuerdo a lo deseado
     * @param resultCode Código de resultado de la conexión
     * @param data Información del intent
     */
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
                toastMessage(this, errorMessage + " [" + errorCode + "]");
            } else if (exception instanceof FoursquareUnsupportedVersionException) {/* Unsupported Fourquare app version on the device. */
            } else if (exception instanceof FoursquareInvalidRequestException) {/* Invalid request. */
            } else {/*Error.*/}
        }
    }

    /**
     * Es el método que nos va a regresar el token de acceso necesario para las llamadas HTTP
     * con Fousquare
     *
     * @param resultCode
     * @param data
     */
    private void onCompleteTokenExchange(int resultCode, Intent data) {
        // Se lleva a cabo la solicitud de token
        AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);
        Exception exception = tokenResponse.getException();

        // Si no hay ninguna excepción o problema quiere decir que ya se puede obtener el token
        // de acceso
        if (exception == null) {
            String accessToken = tokenResponse.getAccessToken();

            // Guardamos el token para usarlo posteriormente
            preferences.guardarToken(accessToken);

            // Actualizamos la interfaz
            manageLoginUI();
        } else {
            if (exception instanceof FoursquareOAuthException) {
                // Error en el proceso de autenticación OAuth
                Log.d("onCompleteTokenExchange", "OAuth error");
                String errorMessage = ((FoursquareOAuthException) exception).getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                toastMessage(this, errorMessage + " [" + errorCode + "]");
            } else {
                // Other tipo de excepciones
                Log.i("onCompleteTokenExchange", "Toast Error");
                toastError(this, exception);
            }
        }
    }

    /**
     * Exchange a code for an OAuth Token. Note that we do not recommend you
     * do this in your app, rather do the exchange on your server. Added here
     * for demo purposes.
     *
     * @param code
     *          The auth code returned from the native auth flow.
     */

    private void performTokenExchange(String code) {
        Intent intent = FoursquareOAuth.getTokenExchangeIntent(this, CLIENT_ID, CLIENT_SECRET, code);
        startActivityForResult(intent, REQUEST_CODE_FSQ_TOKEN_EXCHANGE);
        Log.d("performTokenExchange", intent.toString());
    }

    public static void toastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Context context, Throwable t) {
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
    }



}
