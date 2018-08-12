package com.marivr.tiendita.Datos.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DB_NOMBRE = "productos.db";
    private static final int DB_VERSION = 1;


    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE "
                        + ProductosContract.Entrada.NOMBRE_TABLA + "("
                        + ProductosContract.Entrada.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + ProductosContract.Entrada.COLUMNA_NOMBRE + " TEXT, "
                        + ProductosContract.Entrada.COLUMNA_FOTO + " TEXT, "
                        + ProductosContract.Entrada.COLUMNA_PRECIO + " REAL" + ")";

    public static final String CREATE_COMPRA_TABLE = "CREATE TABLE "
            + CompraContract.Entrada.NOMBRE_TABLA + "("
            + CompraContract.Entrada.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CompraContract.Entrada.COLUMNA_NOMBRE + " TEXT, "
            + CompraContract.Entrada.COLUMNA_CANTIDAD + " INTEGER, "
            + CompraContract.Entrada.COLUMNA_PRECIO + " REAL, "
            + CompraContract.Entrada.COLUMNA_COMPRAS_ID + " INTEGER" + ")";

    public static final String CREATE_COMPRAS_TABLE = "CREATE TABLE "
            + ComprasContract.Entrada.NOMBRE_TABLA + "("
            + ComprasContract.Entrada.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ComprasContract.Entrada.COLUMNA_NOMBRE + " TEXT, "
            + ComprasContract.Entrada.COLUMNA_LATITUD + " TEXT, "
            + ComprasContract.Entrada.COLUMNA_LONGITUD + " REAL, "
            + ComprasContract.Entrada.COLUMNA_FECHA + " DATE, "
            + ComprasContract.Entrada.COLUMNA_ELEMENTOS + " INTEGER, "
            + ComprasContract.Entrada.COLUMNA_TOTAL + " REAL" + ")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ProductosContract.Entrada.NOMBRE_TABLA;

    private static final String SQL_DELETE_COMPRA_ENTRIES =
            "DROP TABLE IF EXISTS " + CompraContract.Entrada.NOMBRE_TABLA;

    private static final String SQL_DELETE_COMPRAS_ENTRIES =
            "DROP TABLE IF EXISTS " + ComprasContract.Entrada.NOMBRE_TABLA;


    public DataBaseHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_COMPRAS_TABLE);
        sqLiteDatabase.execSQL(CREATE_COMPRA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_COMPRA_ENTRIES);
        sqLiteDatabase.execSQL(SQL_DELETE_COMPRAS_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
