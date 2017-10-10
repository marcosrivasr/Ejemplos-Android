package com.marivr.ejemplocamara;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {


    private Button bTomar, bSeleccionar;
    private ImageView ivFoto;
    private TextView tvUrl;

    //TODO: 1.- Añadir permisos al manifiesto


    //TODO: 2.- Definir constantes de permisos
    private static final int SELECT_PHOTO = 100;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    //TODO: 3.- Obtenemos permisos de storage
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //TODO: 9.- Obtenemos permisos de cámara
    private static final int REQUEST_CAMERA = 200;
    private static final String FILE_PROVIDER = "com.marivr.ejemplocamara.fileprovider";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bTomar = (Button) findViewById(R.id.bTomar);
        bSeleccionar = (Button) findViewById(R.id.bSeleccionar);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);
        tvUrl = (TextView) findViewById(R.id.tvUrl);

        bSeleccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 4.- Mandamos a validar permisos
                validarPermisosStorage();
            }
        });






        bTomar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: 10.- Mandamos a validar permisos
                validarPermisosCamara();
            }
        });
    }


    //TODO: 5.- Validamos si el usuario ya tiene permisos, se le negó o no ha pedido
    public void validarPermisosStorage() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Debemos mostrar un mensaje?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Mostramos una explicación de que no aceptó dar permiso para acceder a la librería
            } else {
                // Pedimos permiso
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }
        }else{
            iniciarIntentSeleccionarFotos();
        }
    }

    //TODO: 6.- Llama al intent para seleccionar fotos
    private void iniciarIntentSeleccionarFotos() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    //TODO: 7.- Revisamos si se le dio permiso al usuario o no
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                // Si la petición se cancela se regresa un arreglo vacío
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido
                    iniciarIntentSeleccionarFotos();
                } else {
                    // Permiso negado
                }
                return;
            // TODO 14.- Validamos el permiso para el acceso a la cámara
            case REQUEST_CAMERA:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Se concedió acceso
                    iniciarIntentTomarFoto();
                } else {
                    // permiso negado
                }
                return;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            //TODO: 8.- Si obtuvimos una imagen entonces la procesamos
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri imagenSeleccionada = imageReturnedIntent.getData();
                    try{
                        InputStream imagenStream = getContentResolver().openInputStream(imagenSeleccionada);
                        Bitmap imagen = BitmapFactory.decodeStream(imagenStream);
                        ivFoto.setImageBitmap(imagen);
                        tvUrl.setText(imagenSeleccionada.toString());

                    }catch (FileNotFoundException fnte){
                        Toast.makeText(this, fnte.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                    return;
                }
                // TODO 15.- Si obtuvimos la imagen y la guardamos la mostramos
            case REQUEST_CAMERA:
                if(resultCode == RESULT_OK){
                    Picasso.with(this).load(tvUrl.getText().toString()).into(ivFoto);
                }
                return;
        }
    }




    //TODO: 11.- Validamos permisos de cámara
    public void validarPermisosCamara() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA);
            }
        }else{
            iniciarIntentTomarFoto();
        }
    }

    //TODO: 12.- Iniciamos intent para tomar foto
    private  void iniciarIntentTomarFoto(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Validamos que hay una actividad de cámara
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            // Creamos un nuevo objeto para almacenar la foto
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error creando archivo
            }
            // Si salió bien
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, FILE_PROVIDER, photoFile);
                // Mandamos llamar el intent
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_CAMERA);
            }
        }
    }

    //TODO: 13.- Función para crear archivo y URL
    private File createImageFile() throws IOException {
        // Creamos el archivo
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreImagen = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                nombreImagen,  /* prefijp */
                ".jpg",         /* sufijo */
                storageDir      /* directorio */
        );

        // Obtenemos la URL
        String urlName = "file://" + image.getAbsolutePath();
        tvUrl.setText(urlName);
        return image;
    }
}
