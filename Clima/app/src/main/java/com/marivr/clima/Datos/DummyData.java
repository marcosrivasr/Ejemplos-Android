package com.marivr.clima.Datos;

import java.util.ArrayList;

/**
 * Created by marivr on 13/08/2017.
 */

public class DummyData {

    private ArrayList<Ciudad> ciudades;

    public static ArrayList<Ciudad> getCiudades(){
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
        ciudades.add(new Ciudad(3530597, "Mexico City"));
        ciudades.add(new Ciudad(3128760, "Barcelona"));
        ciudades.add(new Ciudad(1816670, "Beijing"));

        return ciudades;
    }
    public static String url(String id){
        return "http://api.openweathermap.org/data/2.5/weather?id="+id+"&APPID=79dc5b927ee95b81ad03f7213c49b87b";
    }

}
