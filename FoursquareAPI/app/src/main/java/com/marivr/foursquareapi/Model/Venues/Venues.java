package com.marivr.foursquareapi.Model.Venues;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by marivr on 31/08/2017.
 */

public class Venues {

    private ArrayList<Venue> venues;

    public Venues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }
}
