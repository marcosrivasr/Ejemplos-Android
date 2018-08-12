package com.marivr.foursquareapi.Networking;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by marivr on 31/08/2017.
 */

public class HttpRequest {

    public static String GET(OkHttpClient client, HttpUrl url){
        try{
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }catch (IOException ioe){
            Log.e("GET", ioe.getMessage());
        }
        return null;
    }

    public static String POST(OkHttpClient client, HttpUrl url, RequestBody requestBody) throws IOException{
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
