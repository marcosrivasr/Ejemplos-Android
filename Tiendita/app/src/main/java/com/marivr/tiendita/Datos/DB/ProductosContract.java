package com.marivr.tiendita.Datos.DB;

import android.provider.BaseColumns;


public class ProductosContract {
    private ProductosContract(){}

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "productos";
        
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_FOTO = "foto";
        public static final String COLUMNA_PRECIO = "precio";
    }
}
