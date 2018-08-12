package com.marivr.foursquareapi.Model.Venues;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marivr on 31/08/2017.
 */

public class StatsResponse {

    @SerializedName("response")
    private Stats stats;

    public StatsResponse(Stats stats) {
        this.stats = stats;
    }

    public Stats getStats() {
        return stats;
    }
}
