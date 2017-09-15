package com.marivr.flickr.Utilities;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marivr on 14/08/2017.
 */

public class CustomParser extends JSONObject {

    private String url;
    //private JSONObject jsonObject;

    private static final String TAG = "Parser";

    public CustomParser(String url){
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

    public JSONArray getArregloDeObjeto(String objeto, String campo){
        try{
            JSONObject json = new JSONObject(this.url);

            return json.getJSONObject(objeto).getJSONArray(campo);

        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }
    /**
     * Returns the value mapped by {@code name} if it exists and is a {@code
     * JSONObject}, or throws otherwise.
     * @param objeto primer elemento
     *
     * @throws JSONException if the mapping doesn't exist or is not a {@code
     *     JSONObject}.
     */
    public String getValorDeObjetoN2DeArreglo(String objeto, String objeto2, int index, String campo){
        try{
            JSONObject json = new JSONObject(this.url);

            return json.getJSONObject(objeto).getJSONArray(objeto2).getJSONObject(index).getString(campo);

        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }

    public JSONObject getObjetoDeObjeto(String objeto, String objeto2){
        try{
            JSONObject json = new JSONObject(this.url);

            return json.getJSONObject(objeto).getJSONObject(objeto2);

        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }

    public String getValorDeObjetoN2(String objeto, String objeto2, String campo){
        try{
            return getObjetoDeObjeto(objeto, objeto2).getString(campo);
        }catch(Exception e){
            Log.i(TAG, e.toString());
            return null;
        }
    }


}
