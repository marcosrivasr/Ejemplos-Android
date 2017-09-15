package com.marivr.foursquareapi.Model.Venues;

/**
 * Created by marivr on 31/08/2017.
 */

public class Category {
    private String id;
    private String name;
    private String pluralName;
    private String shortName;
    private Icon icon;

    public Category(String id, String name, String pluralName, String shortName, Icon icon) {
        this.id = id;
        this.name = name;
        this.pluralName = pluralName;
        this.shortName = shortName;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPluralName() {
        return pluralName;
    }

    public String getShortName() {
        return shortName;
    }

    public Icon getIcon() {
        return icon;
    }
}
