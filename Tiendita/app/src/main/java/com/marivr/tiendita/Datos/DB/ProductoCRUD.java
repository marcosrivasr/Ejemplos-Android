package com.marivr.tiendita.Datos.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marivr.tiendita.Datos.Producto;

import java.util.ArrayList;

/**
 * Created by marivr on 16/09/2017.
 */

public class ProductoCRUD {


    private DataBaseHelper helper;

    public ProductoCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void crearNuevoElemento(Producto producto){
        // Gets the data repository in write mode
        SQLiteDatabase db = helper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        //values.put(ProductosContract.Entrada.COLUMNA_ID, producto.getId());
        values.put(ProductosContract.Entrada.COLUMNA_NOMBRE, producto.getNombre());
        values.put(ProductosContract.Entrada.COLUMNA_FOTO, producto.getFoto());
        values.put(ProductosContract.Entrada.COLUMNA_PRECIO, producto.getPrecio());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ProductosContract.Entrada.NOMBRE_TABLA, null, values);
    }

    public ArrayList<Producto> leerProductos(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_FOTO,
                ProductosContract.Entrada.COLUMNA_PRECIO
        };

        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA,
                projection,
                null,
                null,
                null,
                null,
                null);

        while (c.moveToNext()){
            productos.add(new Producto(
                    c.getInt(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_FOTO)),
                    c.getFloat(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_PRECIO))
            ));
        }
        db.close();
        return productos;
    }

    public ArrayList<String> leerNombreProductos(){
        ArrayList<String> productos = new ArrayList<String>();
        SQLiteDatabase db = helper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_FOTO,
                ProductosContract.Entrada.COLUMNA_PRECIO
        };

        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA,
                projection,
                null,
                null,
                null,
                null,
                null);

        while (c.moveToNext()){
            productos.add(c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)));
        }
        db.close();
        return productos;
    }

    public void deleteProducto(int id){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // Inserta la nueva fila, regresando el valor de la primary key
        db.delete(
                ProductosContract.Entrada.NOMBRE_TABLA,
                "id = ?",
                new String[]{String.valueOf(id)}
        );

        // cierra conexión
        db.close();
    }

    public void updateProducto(Producto item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(ProductosContract.Entrada.COLUMNA_ID, item.getId());
        values.put(ProductosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(ProductosContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(ProductosContract.Entrada.COLUMNA_FOTO, item.getFoto());

        // TODO: 19.- Actualizamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.update(
                ProductosContract.Entrada.NOMBRE_TABLA,
                values,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );

        // cierra conexión
        db.close();
    }

    public Producto getProducto(String id){
        Producto item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_PRECIO,
                ProductosContract.Entrada.COLUMNA_FOTO
        };

        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{id}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Producto(
                    c.getInt(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_FOTO)),
                    c.getFloat(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_PRECIO))
            );
        }

        c.close();
        return item;
    }
}
