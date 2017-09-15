package com.marivr.chat;

/**
 * Created by marivr on 17/08/2017.
 */

public class Mensaje {

    private String texto;
    private boolean izquierda;

    public Mensaje() {
    }

    public Mensaje(boolean izquierda, String texto){
        this.izquierda = izquierda;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isIzquierda() {
        return izquierda;
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }
}
