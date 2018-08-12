package com.marivr.foursquareapi.Model.Photos;
/**
 * Created by marivr on 31/08/2017.
 */

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private Photo photo;

    public User(String id, String firstName, String lastName, Photo photo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Photo getPhoto() {
        return photo;
    }
}
