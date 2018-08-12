package com.marivr.flickr.Datos;

import android.graphics.Bitmap;

/**
 * Created by marivr on 20/08/2017.
 */

public class Categoria {

    private String texto;
    private String id;
    private String icono;

    public Categoria(String texto, String id, String icono) {
        this.texto = texto;
        this.id = id;
        this.icono = icono;
    }

    public String getTexto(){
        return this.texto;
    }

    public String getIcono() {
        return icono;
    }

    public String getId() {
        return id;
    }
}
