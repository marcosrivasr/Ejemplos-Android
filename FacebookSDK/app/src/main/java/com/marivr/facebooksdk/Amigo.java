package com.marivr.facebooksdk;

/**
 * Created by marivr on 15/09/2017.
 */

public class Amigo {

    private String id;
    private String name;
    private Picture picture;

    public Amigo(String id, String name, Picture picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Picture getPicture() {
        return picture;
    }
}
