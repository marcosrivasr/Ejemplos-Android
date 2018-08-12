package com.marivr.foursquareapi.Model.Venues;

import com.google.gson.annotations.SerializedName;
import com.marivr.foursquareapi.Model.Photos.User;

import java.util.ArrayList;

/**
 * Created by marivr on 31/08/2017.
 */

public class Stats{

    private String checkinsCount;

    @SerializedName("topVisitors")
    private ArrayList<User> users;

    public Stats(String checkinsCount, ArrayList<User> users) {
        this.checkinsCount = checkinsCount;
        this.users = users;
    }

    public String getCheckinsCount() {
        return checkinsCount;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
