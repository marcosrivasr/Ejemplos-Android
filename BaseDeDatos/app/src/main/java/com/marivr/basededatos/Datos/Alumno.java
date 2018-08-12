package com.marivr.basededatos.Datos;


import java.util.UUID;

import static android.R.attr.description;
import static android.R.id.summary;

/**
 * Created by marivr on 09/09/2017.
 */
// TODO: 1.- Creación de clase Alumno
public class Alumno {

    private String id;
    private String nombre;

    public Alumno(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
