package com.marivr.basededatos.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class AlumnoCRUD {

    private DataBaseHelper helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public AlumnoCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public void newAlumno(Alumno item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(AlumnosContract.Entrada.COLUMNA_ID, item.getId());
        values.put(AlumnosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());

        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(AlumnosContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
    }

    public  ArrayList<Alumno> getAlumnos(){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<Alumno> items = new ArrayList<Alumno>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                AlumnosContract.Entrada.COLUMNA_ID,
                AlumnosContract.Entrada.COLUMNA_NOMBRE,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                AlumnosContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                null, //texto para filtrar
                null, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_NOMBRE))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }

    public Alumno getAlumno(String id){
        Alumno item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                AlumnosContract.Entrada.COLUMNA_ID,
                AlumnosContract.Entrada.COLUMNA_NOMBRE,
        };

        // TODO: 18.- usamos los parámetros para obtener una sentencia "WHERE"
        Cursor c = db.query(
                AlumnosContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Alumno(
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(AlumnosContract.Entrada.COLUMNA_NOMBRE))
            );
        }

        c.close();
        return item;
    }

    public void updateAlumno(Alumno item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(AlumnosContract.Entrada.COLUMNA_ID, item.getId());
        values.put(AlumnosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());

        // TODO: 19.- Actualizamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.update(
                AlumnosContract.Entrada.NOMBRE_TABLA,
                values,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );

        // cierra conexión
        db.close();
    }

    public void deleteAlumno(Alumno item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 20.- Eliminamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.delete(
                AlumnosContract.Entrada.NOMBRE_TABLA,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );

        // cierra conexión
        db.close();
    }


}
