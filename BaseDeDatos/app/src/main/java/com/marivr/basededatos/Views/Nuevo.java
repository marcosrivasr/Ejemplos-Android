package com.marivr.basededatos.Views;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marivr.basededatos.Datos.AlumnosContract;
import com.marivr.basededatos.Datos.DataBaseHelper;
import com.marivr.basededatos.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Nuevo extends AppCompatActivity {

    private EditText tvId, tvNombre, tvAvatar;
    private Button bAdd, bSearch;
    private static final int SELECT_PHOTO = 100;

    private DataBaseHelper helper;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        tvId = (EditText) findViewById(R.id.tvMatricula);
        tvNombre = (EditText) findViewById(R.id.tvNombre);
        tvAvatar = (EditText) findViewById(R.id.tvAvatar);

        bAdd = (Button) findViewById(R.id.bAdd);

        bSearch = (Button) findViewById(R.id.bSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                verifyStoragePermissions(Nuevo.this);
            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToDB();
                startActivity(new Intent(Nuevo.this, Main.class));
            }
        });

        helper = new DataBaseHelper(this);
    }

    public void writeToDB(){
        // Gets the data repository in write mode
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AlumnosContract.Entrada.COLUMNA_ID, tvId.getText().toString());
        values.put(AlumnosContract.Entrada.COLUMNA_NOMBRE, tvNombre.getText().toString());
        values.put(AlumnosContract.Entrada.COLUMNA_AVATAR, tvAvatar.getText().toString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(AlumnosContract.Entrada.NOMBRE_TABLA, null, values);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try{
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap yourSelectedImage = BitmapFactory.decodeStream(imageStream);
                        tvAvatar.setText(selectedImage.toString());
                    }catch (FileNotFoundException fnte){
                        Toast.makeText(this, fnte.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
        }
    }


    public void verifyStoragePermissions(Activity activity) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                 // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            iniciarIntentFotos();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        switch (requestCode) {
        case REQUEST_EXTERNAL_STORAGE: {
             // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                iniciarIntentFotos();
            } else {

                 // permission denied, boo! Disable the
                 // functionality that depends on this permission.
            }
            return;
        }

        // other 'case' lines to check for other
        // permissions this app might request
    }
    }

    private void iniciarIntentFotos() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }
}
