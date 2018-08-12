package com.marivr.tiendita.Datos.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marivr.tiendita.Datos.CompraRegistro;

import java.util.ArrayList;

/**
 * Created by marivr on 17/09/2017.
 */

public class CompraCRUD {
    private DataBaseHelper helper;

    public CompraCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void crearNuevoElemento(CompraRegistro item){
        // Gets the data repository in write mode
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(ProductosContract.Entrada.COLUMNA_ID, producto.getId());
        values.put(CompraContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(CompraContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(CompraContract.Entrada.COLUMNA_CANTIDAD, item.getCantidad());
        values.put(CompraContract.Entrada.COLUMNA_COMPRAS_ID, item.getCompras_id());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(CompraContract.Entrada.NOMBRE_TABLA, null, values);
    }

    public ArrayList<CompraRegistro> leerRegistros(int id){
        ArrayList<CompraRegistro> items = new ArrayList<CompraRegistro>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                CompraContract.Entrada.COLUMNA_ID,
                CompraContract.Entrada.COLUMNA_NOMBRE,
                CompraContract.Entrada.COLUMNA_PRECIO,
                CompraContract.Entrada.COLUMNA_CANTIDAD,
                CompraContract.Entrada.COLUMNA_COMPRAS_ID,
        };

        Cursor c = db.query(
                CompraContract.Entrada.NOMBRE_TABLA,
                projection,
                " compras_id= ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        while (c.moveToNext()){
            items.add(new CompraRegistro(
                    c.getInt(c.getColumnIndexOrThrow(CompraContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(CompraContract.Entrada.COLUMNA_NOMBRE)),
                    c.getInt(c.getColumnIndexOrThrow(CompraContract.Entrada.COLUMNA_CANTIDAD)),
                    c.getFloat(c.getColumnIndexOrThrow(CompraContract.Entrada.COLUMNA_PRECIO)),
                    c.getInt(c.getColumnIndexOrThrow(CompraContract.Entrada.COLUMNA_COMPRAS_ID))
            ));
        }
        c.close();
        return items;
    }
}
