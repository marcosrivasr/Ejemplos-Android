package com.marivr.tiendita.Datos;

/**
 * Created by marivr on 17/09/2017.
 */

public class Compra {
    private int id;
    private Producto producto;
    private int cantidad;
    private float total;



    public Compra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Compra(int id, Producto producto, int cantidad) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getTotal() {
        return cantidad * producto.getPrecio();
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
