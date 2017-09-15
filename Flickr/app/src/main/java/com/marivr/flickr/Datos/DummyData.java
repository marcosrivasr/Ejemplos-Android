package com.marivr.flickr.Datos;

import java.util.ArrayList;

/**
 * Created by marivr on 13/08/2017.
 */

public class DummyData {



    public static String url(String id){
        return "https://api.flickr.com/services/rest/?method=flickr.photos.search&tags="+id+"&api_key=aed598513bbd2430b6494785e5c5f80c&per_page=20&format=json&nojsoncallback=1";
    }

    public static String detalleURL(String id, String secret){
        return "https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key=aed598513bbd2430b6494785e5c5f80c&photo_id="+id+"&secret="+secret+"&format=json&nojsoncallback=1W";
    }

    public static ArrayList<Categoria> getCategorias(){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        categorias.add(new Categoria("Gente", "portraits", "female_64px.png"));
        categorias.add(new Categoria("Cielo", "sky", "cloud_64px.png"));
        categorias.add(new Categoria("Playas", "beaches", "beach-seat_64px.png"));
        categorias.add(new Categoria("Lugares", "cities", "bicycle_64px.png"));
        categorias.add(new Categoria("Comida", "comida", "bowl_64px.png"));
        categorias.add(new Categoria("Edificios", "buildings", "brick-wall_64px.png"));
        categorias.add(new Categoria("Arte", "art", "brush-roll_64px.png"));
        return categorias;
    }

}
