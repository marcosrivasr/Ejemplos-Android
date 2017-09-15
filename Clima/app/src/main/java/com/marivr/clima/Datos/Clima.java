package com.marivr.clima.Datos;

/**
 * Created by marivr on 14/08/2017.
 */

public class Clima {

    private float temperatura;
    private String descripcion;
    private String ciudad;

    public Clima(float temperatura, String descripcion, String ciudad) {
        this.temperatura = temperatura;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public int getTemperaturaEnCelcius() {
        return (int)Math.abs(temperatura - 273.15);
    }


    public String getDescripcion() {
        return descripcion;
    }



    public String getCiudad() {
        return ciudad;
    }


}
