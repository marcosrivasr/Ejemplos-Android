package com.marivr.tiendita.Datos;


import java.util.Date;

/**
 * Created by marivr on 17/09/2017.
 */

public class ComprasRegistro {
    private int id;
    private String nombre;
    private String latitud;
    private String longitud;
    private String fecha;
    private int elementos;
    private float total;

    public ComprasRegistro(String nombre, String latitud, String longitud, String fecha, int elementos, float total) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.elementos = elementos;
        this.total = total;
    }

    public ComprasRegistro(int id, String nombre, String latitud, String longitud, String fecha, int elementos, float total) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
        this.elementos = elementos;
        this.total = total;
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

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String  getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getElementos() {
        return elementos;
    }

    public void setElementos(int elementos) {
        this.elementos = elementos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
