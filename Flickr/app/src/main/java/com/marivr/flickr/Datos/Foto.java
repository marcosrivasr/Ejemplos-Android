package com.marivr.flickr.Datos;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by marivr on 20/08/2017.
 */

public class Foto implements Parcelable{
    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String isPublic;
    private Drawable image;


    public Foto(String id, String owner, String secret, String server, String farm, String title, String isPublic) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.isPublic = isPublic;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.owner);
        dest.writeString(this.secret);
        dest.writeString(this.server);
        dest.writeString(this.farm);
        dest.writeString(this.title);
        dest.writeString(this.isPublic);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public Foto(Parcel in){
        this.id = in.readString();
        this.owner = in.readString();
        this.secret = in.readString();
        this.server = in.readString();
        this.farm = in.readString();
        this.title = in.readString();
        this.isPublic = in.readString();
    }

    public static final Parcelable.Creator<Foto> CREATOR
            = new Parcelable.Creator<Foto>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Foto createFromParcel(Parcel in) {
            return new Foto(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Foto[] newArray(int size) {
            return new Foto[size];
        }
    };




    public String getImagenURL(){
        return "http://farm"+this.farm+".staticflickr.com/"+this.server+"/"+this.id+"_"+this.secret+".jpg";

    }

    public void setImagen(Drawable image){
        this.image = image;
    }

    public Drawable getImagen(){
        return this.image;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public String getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public String getIsPublic() {
        return isPublic;
    }
}
