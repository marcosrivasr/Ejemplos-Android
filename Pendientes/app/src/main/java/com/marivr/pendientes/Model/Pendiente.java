package com.marivr.pendientes.Model;

import java.util.Date;

/**
 * Created by marivr on 24/09/2017.
 */

public class Pendiente {

    private String id;
    private String texto;
    private boolean completa;
    private String fecha;
    private int prioridad;

    public Pendiente(){

    }
    public Pendiente(String texto, boolean completa, String fecha, int prioridad) {
        this.texto = texto;
        this.completa = completa;
        this.fecha = fecha;
        this.prioridad = prioridad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}
