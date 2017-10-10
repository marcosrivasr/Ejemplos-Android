package com.marivr.basededatos.Datos;

import android.provider.BaseColumns;

/**
 * Created by marivr on 09/09/2017.
 */

// TODO: 2.- Creación de clase AlumnosContract
public final class AlumnosContract {
    private AlumnosContract(){}

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "alumnos";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
    }
}
