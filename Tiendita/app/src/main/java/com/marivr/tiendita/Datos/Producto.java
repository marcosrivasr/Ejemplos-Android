package com.marivr.tiendita.Datos;

/**
 * Created by marivr on 09/09/2017.
 */

public class Producto {

    private int id;
    private String nombre;
    private String foto;
    private float precio;

    public Producto(int id, String nombre, String foto, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.precio = precio;
    }

    public Producto(String nombre, String foto, float precio) {
        this.nombre = nombre;
        this.foto = foto;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
