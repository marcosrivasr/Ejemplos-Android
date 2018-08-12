package com.marivr.materialdesign.Modelo;

import com.marivr.materialdesign.R;

import java.util.ArrayList;

/**
 * Created by marivr on 27/08/2017.
 */

public class DummyData {
    private static ArrayList<String> ciudades;
    private static ArrayList<Perfil> usuarios;

    public static ArrayList<String> obtenerCiudades(){
        ciudades = new ArrayList<>();
        ciudades.add("Mexico");
        ciudades.add("Chicago");
        ciudades.add("Barcelona");
        ciudades.add("Paris");
        ciudades.add("Moscú");
        ciudades.add("Seattle");
        ciudades.add("Dallas");
        ciudades.add("Buenos Aires");
        ciudades.add("Lima");
        ciudades.add("Lisboa");
        ciudades.add("Atenas");
        ciudades.add("Shangai");
        ciudades.add("Korea");
        ciudades.add("Sudáfrica");
        ciudades.add("Montevideo");
        ciudades.add("Cartagena");
        ciudades.add("Barranquilla");

        return ciudades;
    }

    public static ArrayList<Perfil> obtenerUsuarios(){
        usuarios = new ArrayList<>();
        usuarios.add(new Perfil(R.drawable.profile01, "Marcos Rivas"));
        usuarios.add(new Perfil(R.drawable.profile01, "Omar Pozos"));
        usuarios.add(new Perfil(R.drawable.profile01, "Tania Bañales"));
        usuarios.add(new Perfil(R.drawable.profile01, "Juan Manuel"));
        usuarios.add(new Perfil(R.drawable.profile01, "Abacú Castañeda"));
        usuarios.add(new Perfil(R.drawable.profile01, "Carlos Fernandez"));
        usuarios.add(new Perfil(R.drawable.profile01, "Mariana Granados"));
        usuarios.add(new Perfil(R.drawable.profile01, "Fernanda Vela"));
        usuarios.add(new Perfil(R.drawable.profile01, "Victor del Ángel"));
        usuarios.add(new Perfil(R.drawable.profile01, "Francisco Rodríguez"));
        usuarios.add(new Perfil(R.drawable.profile01, "Omar Tijera"));


        return usuarios;
    }
}
