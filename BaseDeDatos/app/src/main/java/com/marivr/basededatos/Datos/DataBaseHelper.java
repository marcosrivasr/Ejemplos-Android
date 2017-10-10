package com.marivr.basededatos.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marivr on 09/09/2017.
 */
// TODO: 3.- Creamos clase que extiende a SQLiteOpenHelper
public class DataBaseHelper extends SQLiteOpenHelper{

    // TODO: 4.- declaramos variables para nombre y versión de DB
    private static final String DB_NOMBRE = "alumnos.db";
    private static final int DB_VERSION = 1;


    // TODO: 5.- Creación de sentencia SQL para crear tabla
    public static final String CREATE_ALUMNOS_TABLE = "CREATE TABLE "
                        + AlumnosContract.Entrada.NOMBRE_TABLA + "("
                        + AlumnosContract.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, "
                        + AlumnosContract.Entrada.COLUMNA_NOMBRE + " TEXT" +") ";

    // TODO: 6.- Creación de sentencia SQL para eliminar tabla
    private static final String SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS " + AlumnosContract.Entrada.NOMBRE_TABLA;

    // TODO: 7.- Constructor
    public DataBaseHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    // TODO: 8.- Para mandar a crear las tablas
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ALUMNOS_TABLE);
    }

    // TODO: 9.- Para actualizar las tablas cuando cambie de versión la DB
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
