package com.marivr.facebooksdk;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by marivr on 15/09/2017.
 */

public class ResponseFriends {
    @SerializedName("data")
    private ArrayList<Amigo> amigos;

    public ResponseFriends(ArrayList<Amigo> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<Amigo> getAmigos() {
        return amigos;
    }
}
