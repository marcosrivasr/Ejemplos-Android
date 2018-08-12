package com.marivr.tiendita.Datos;

/**
 * Created by marivr on 17/09/2017.
 */

public class CompraRegistro {
    private int id;
    private String nombre;
    private int cantidad;
    private float precio;
    private int compras_id;

    public CompraRegistro(String nombre, int cantidad, float precio, int compras_id) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.compras_id = compras_id;
    }

    public CompraRegistro(int id, String nombre, int cantidad, float precio, int compras_id) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.compras_id = compras_id;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCompras_id() {
        return compras_id;
    }

    public void setCompras_id(int compras_id) {
        this.compras_id = compras_id;
    }
}
