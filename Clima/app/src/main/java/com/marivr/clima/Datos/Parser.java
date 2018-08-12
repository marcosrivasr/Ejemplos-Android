package com.marivr.clima.Datos;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by marivr on 14/08/2017.
 */

public class Parser {

    private String url;
    private JSONObject jsonObject;

    private static final String TAG = "Parser";

    public Parser(String url){
        this.url = url;

    }


    public String getValordeArreglo(String arreglo, int posicion, String campo){
        try{
            JSONObject json = new JSONObject(this.url);
            Log.i(TAG, json.getJSONArray(arreglo).getJSONObject(posicion).getString(campo));
            return json.getJSONArray(arreglo).getJSONObject(posicion).getString(campo);
        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }

    public String getValordeObjeto(String objeto, String campo){
        try{
            JSONObject json = new JSONObject(this.url);
            Log.i(TAG, json.getJSONObject(objeto).getString(campo));
            return json.getJSONObject(objeto).getString(campo);

        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }

    public String getValordeCampo(String campo){
        try{
            JSONObject json = new JSONObject(this.url);
            Log.i(TAG, json.getString(campo));
            return json.getString(campo);

        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }


}
