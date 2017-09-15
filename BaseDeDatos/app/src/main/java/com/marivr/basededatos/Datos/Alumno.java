package com.marivr.basededatos.Datos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.UUID;

import static android.R.attr.description;
import static android.R.id.summary;

/**
 * Created by marivr on 09/09/2017.
 */

public class Alumno {

    private String id;
    private String nombre;
    private String avatar;

    public Alumno(String id, String nombre, String avatar) {
        this.id = id;
        this.nombre = nombre;
        this.avatar = avatar;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
