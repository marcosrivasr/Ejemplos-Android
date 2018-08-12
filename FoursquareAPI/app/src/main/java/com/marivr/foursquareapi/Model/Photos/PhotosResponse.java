package com.marivr.foursquareapi.Model.Photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marivr on 01/09/2017.
 */

public class PhotosResponse {

    private Photos response;

    public PhotosResponse(Photos response) {
        this.response = response;
    }

    public Photos getResponse() {
        return response;
    }
}
