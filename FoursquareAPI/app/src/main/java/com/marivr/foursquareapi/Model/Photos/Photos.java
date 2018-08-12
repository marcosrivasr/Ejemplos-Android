package com.marivr.foursquareapi.Model.Photos;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;

/**
 * Created by marivr on 01/09/2017.
 */

public class Photos {

    private PhotosData photos;

    public Photos(PhotosData photos) {
        this.photos = photos;
    }

    public PhotosData getPhotos() {
        return photos;
    }
}
