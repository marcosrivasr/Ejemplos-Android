package com.marivr.flickr.Datos;

/**
 * Created by marivr on 20/08/2017.
 */

public class FotoDetalle extends Foto {

    private String userName;
    private String realName;
    private String iconServer;
    private String description;
    private String nsid;
    private String iconFarm;

    public FotoDetalle(String id,
                       String owner,
                       String secret,
                       String server,
                       String farm,
                       String title,
                       String isPublic,
                       String userName,
                       String realName,
                       String iconServer,
                       String nsid,
                       String iconFarm,
                       String description){

        super(id, owner, secret, server, farm, title, isPublic);

        this.userName = userName;
        this.realName = realName;
        this.iconServer = iconServer;
        this.description = description;
        this.nsid = nsid;
        this.iconFarm = iconFarm;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public String getRealName() {
        return realName;
    }

    public String getIconServer() {
        return iconServer;
    }

    public String getNsid() {
        return nsid;
    }

    public String getIconFarm() {
        return iconFarm;
    }

    public String getFotoPerfil(){
        return "http://farm"+this.getIconFarm()+".staticflickr.com/"+getIconServer()+"/buddyicons/"+getNsid()+".jpg";
    }
}
