package com.marivr.foursquareapi.Model.Venues;

/**
 * Created by marivr on 31/08/2017.
 */

public class Icon {

    private String prefix;
    private String suffix;

    public Icon(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
