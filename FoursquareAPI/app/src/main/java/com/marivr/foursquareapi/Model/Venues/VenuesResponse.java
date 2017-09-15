package com.marivr.foursquareapi.Model.Venues;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marivr on 31/08/2017.
 */

public class VenuesResponse {

    @SerializedName("response")
    private Venues venues;

    public VenuesResponse(Venues venues) {
        this.venues = venues;
    }

    public Venues getVenues() {
        return venues;
    }
}
