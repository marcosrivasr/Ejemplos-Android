package com.marivr.clima.Datos;

/**
 * Created by marivr on 13/08/2017.
 */

public class Ciudad{

    private int id;
    private String nombre;

    public Ciudad(){}

    public Ciudad(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public int getId(){return this.id;}

    public String getNombre(){return this.nombre;}


}
