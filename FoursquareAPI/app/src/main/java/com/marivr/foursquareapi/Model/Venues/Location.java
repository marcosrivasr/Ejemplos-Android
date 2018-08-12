package com.marivr.foursquareapi.Model.Venues;

/**
 * Created by marivr on 03/09/2017.
 */

public class Location {

    private String lat;
    private String lng;
    private String address;
    private String distance;
    private String city;
    private String cc;
    private String country;

    public Location(String lat, String lng, String address, String distance, String city, String cc, String country) {
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.distance = distance;
        this.city = city;
        this.cc = cc;
        this.country = country;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getDistance() {
        return distance;
    }

    public String getCity() {
        return city;
    }

    public String getCc() {
        return cc;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }
}
