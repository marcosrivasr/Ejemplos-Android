package com.marivr.facebooksdk;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by marivr on 15/09/2017.
 */

public class GraphObjectResponse {

    @SerializedName("data")
    private ArrayList<Amigo> amigos;

    public GraphObjectResponse(ArrayList<Amigo> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<Amigo> getAmigos() {
        return amigos;
    }
}
