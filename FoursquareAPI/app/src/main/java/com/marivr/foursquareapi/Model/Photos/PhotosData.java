package com.marivr.foursquareapi.Model.Photos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by marivr on 01/09/2017.
 */

public class PhotosData {

    private String count;

    @SerializedName("items")
    private ArrayList<Photo> items;

    public PhotosData(String count, ArrayList<Photo> items) {
        this.count = count;
        this.items = items;
    }

    public String getCount() {
        return count;
    }

    public ArrayList<Photo> getItems() {
        return items;
    }
}
