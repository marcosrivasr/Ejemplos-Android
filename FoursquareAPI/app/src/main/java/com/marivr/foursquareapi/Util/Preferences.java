package com.marivr.foursquareapi.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by marivr on 31/08/2017.
 */

public class Preferences{

    private SharedPreferences settings;
    public static String USER_PREF="User Preferences";
    private Context context;

    public Preferences(Context context){
        this.context = context;
    }

    public boolean hayToken(){
        settings = context.getSharedPreferences(Preferences.USER_PREF, 0);
        return !TextUtils.isEmpty(settings.getString("token", null));
    }
    public String getToken(){
        SharedPreferences settings = context.getSharedPreferences(Preferences.USER_PREF, 0);
        return settings.getString("token", null);
    }

    public void guardarToken(String accessToken){
        SharedPreferences settings = context.getSharedPreferences(Preferences.USER_PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("token", accessToken);
        Log.d("onCompleteTokenExchange", "token guardado");
        // Commit the edits!
        editor.commit();
    }
}
