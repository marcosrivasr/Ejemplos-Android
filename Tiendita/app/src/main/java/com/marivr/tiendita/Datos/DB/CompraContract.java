package com.marivr.tiendita.Datos.DB;

import android.provider.BaseColumns;

/**
 * Created by marivr on 17/09/2017.
 */

public class CompraContract {
    private CompraContract(){}

    public static class Entrada implements BaseColumns {
        public static final String NOMBRE_TABLA = "compra";

        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_CANTIDAD = "cantidad";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_COMPRAS_ID = "compras_id";
    }
}
