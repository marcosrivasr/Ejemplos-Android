package com.marivr.pruebaexamen;

import java.util.ArrayList;

/**
 * Created by marivr on 11/09/2017.
 */

public class Response {
    private Ubicacion ubicacion;
    int count;
    private ArrayList<Puesto> puestos;

    public Response(Ubicacion ubicacion, int count, ArrayList<Puesto> puestos) {
        this.ubicacion = ubicacion;
        this.count = count;
        this.puestos = puestos;
    }
}

