package com.marivr.foursquareapi.Model.Venues;

import com.marivr.foursquareapi.Model.Photos.Photos;

import java.util.ArrayList;

/**
 * Created by marivr on 31/08/2017.
 */

public class Venue {
    private String id;
    private String name;
    private Location location;
    private ArrayList<Category> categories;
    private Stats stats;

    private Photos photos;


    public Venue(String id, String name, Location location, ArrayList<Category> categories, Stats stats, Photos photos) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.categories = categories;
        this.stats = stats;
        this.photos = photos;
    }

    public Location getLocation() {
        return location;
    }

    public Photos getPhotos() {
        return photos;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public Stats getStats() {
        return stats;
    }
}
