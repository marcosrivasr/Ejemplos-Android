package com.marivr.tiendita.Datos.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marivr.tiendita.Datos.ComprasRegistro;

import java.util.ArrayList;

/**
 * Created by marivr on 17/09/2017.
 */

public class ComprasCRUD {
    private DataBaseHelper helper;

    public ComprasCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void crearNuevoElemento(ComprasRegistro item){
        // Gets the data repository in write mode
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();

        values.put(ComprasContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(ComprasContract.Entrada.COLUMNA_LATITUD, item.getLatitud());
        values.put(ComprasContract.Entrada.COLUMNA_LONGITUD, item.getLongitud());
        values.put(ComprasContract.Entrada.COLUMNA_FECHA, item.getFecha());
        values.put(ComprasContract.Entrada.COLUMNA_ELEMENTOS, item.getElementos());
        values.put(ComprasContract.Entrada.COLUMNA_TOTAL, item.getTotal());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ComprasContract.Entrada.NOMBRE_TABLA, null, values);
    }

    public ArrayList<ComprasRegistro> leerRegistros(){
        ArrayList<ComprasRegistro> items = new ArrayList<ComprasRegistro>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ComprasContract.Entrada.COLUMNA_ID,
                ComprasContract.Entrada.COLUMNA_NOMBRE,
                ComprasContract.Entrada.COLUMNA_LATITUD,
                ComprasContract.Entrada.COLUMNA_LONGITUD,
                ComprasContract.Entrada.COLUMNA_FECHA,
                ComprasContract.Entrada.COLUMNA_ELEMENTOS,
                ComprasContract.Entrada.COLUMNA_TOTAL
        };

        Cursor c = db.query(
                ComprasContract.Entrada.NOMBRE_TABLA,
                projection,
                null,
                null,
                null,
                null,
                null);

        while (c.moveToNext()){
            items.add(new ComprasRegistro(
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_LATITUD)),
                    c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_LONGITUD)),
                    c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_FECHA)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ELEMENTOS)),
                    c.getFloat(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_TOTAL))
            ));
        }
        c.close();
        return items;
    }

    public ComprasRegistro getReciente(){
        ComprasRegistro item = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ComprasContract.Entrada.COLUMNA_ID,
                ComprasContract.Entrada.COLUMNA_NOMBRE,
                ComprasContract.Entrada.COLUMNA_LATITUD,
                ComprasContract.Entrada.COLUMNA_LONGITUD,
                ComprasContract.Entrada.COLUMNA_FECHA,
                ComprasContract.Entrada.COLUMNA_ELEMENTOS,
                ComprasContract.Entrada.COLUMNA_TOTAL
        };

        Cursor c = db.query(
                ComprasContract.Entrada.NOMBRE_TABLA,
                projection,
                null,
                null,
                null,
                null,
                null);


        if(c.moveToLast()){
         item = new ComprasRegistro(
                 c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ID)),
                 c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_NOMBRE)),
                 c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_LATITUD)),
                 c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_LONGITUD)),
                 c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_FECHA)),
                 c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ELEMENTOS)),
                 c.getFloat(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_TOTAL))
         );
        }
        c.close();
        return item;

    }
}
