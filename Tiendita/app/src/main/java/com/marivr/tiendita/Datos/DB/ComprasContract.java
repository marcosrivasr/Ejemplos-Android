package com.marivr.tiendita.Datos.DB;

import android.provider.BaseColumns;

/**
 * Created by marivr on 17/09/2017.
 */

public class ComprasContract {
    private ComprasContract(){}
    public static class Entrada implements BaseColumns {
        public static final String NOMBRE_TABLA = "compras";

        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_LATITUD = "latitud";
        public static final String COLUMNA_LONGITUD = "longitud";
        public static final String COLUMNA_FECHA = "fecha";
        public static final String COLUMNA_ELEMENTOS = "elementos";
        public static final String COLUMNA_TOTAL = "total";
    }
}
