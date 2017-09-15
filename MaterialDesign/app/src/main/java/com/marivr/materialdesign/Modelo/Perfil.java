package com.marivr.materialdesign.Modelo;

/**
 * Created by marivr on 27/08/2017.
 */

public class Perfil {

    private int fotoId;
    private String nombre;

    public Perfil(int fotoId, String nombre) {
        this.fotoId = fotoId;
        this.nombre = nombre;
    }

    public int getFotoId() {
        return fotoId;
    }

    public String getNombre() {
        return nombre;
    }
}
