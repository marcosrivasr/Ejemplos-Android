package com.marivr.basededatos.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marivr on 09/09/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DB_NOMBRE = "alumnos.db";
    private static final int DB_VERSION = 1;


    public static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE "
                        + AlumnosContract.Entrada.NOMBRE_TABLA + "("
                        + AlumnosContract.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, "
                        + AlumnosContract.Entrada.COLUMNA_NOMBRE + " TEXT, "
                        + AlumnosContract.Entrada.COLUMNA_AVATAR + " DATE" + ")";
    private static final String SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS " + AlumnosContract.Entrada.NOMBRE_TABLA;


    public DataBaseHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EMPLOYEE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
