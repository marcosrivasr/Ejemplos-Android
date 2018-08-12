package com.marivr.foursquareapi.Model.Photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marivr on 31/08/2017.
 */

public class Photo {

    private String id;
    private String prefix;
    @SerializedName("suffix")
    private String sufix;
    private User user;

    public Photo(String id, String prefix, String sufix, User user) {
        this.id = id;
        this.prefix = prefix;
        this.sufix = sufix;
        this.user = user;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSufix() {
        return sufix;
    }

    public User getUser() {
        return user;
    }
}
