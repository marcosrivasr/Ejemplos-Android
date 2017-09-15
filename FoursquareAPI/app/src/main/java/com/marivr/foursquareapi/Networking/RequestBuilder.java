package com.marivr.foursquareapi.Networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

import static java.security.AccessController.getContext;

/**
 * Created by marivr on 31/08/2017.
 */

public class RequestBuilder {

    //Login request body
    public static RequestBody LoginBody(String username, String password, String token) {
        return new FormBody.Builder()
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    public static HttpUrl buildURL(String accessToken, String latitude, String longitude) {
        return new HttpUrl.Builder()
                .scheme("https") //http
                .host("api.foursquare.com")
                .addPathSegment("v2")
                .addPathSegment("venues")
                .addPathSegment("search")
                .addQueryParameter("ll", latitude + "," + longitude) //add query parameters to the URL
                .addQueryParameter("oauth_token", accessToken)
                .addQueryParameter("v", "20170831")
                .addQueryParameter("limit", "20")
                //.addEncodedQueryParameter("encodedName", "encodedValue")//add encoded query parameters to the URL
                .build();
    }

    public static HttpUrl buildURLPhotos(String id, String accessToken) {
        return new HttpUrl.Builder()
                .scheme("https") //http
                .host("api.foursquare.com")
                .addPathSegment("v2")
                .addPathSegment("venues")
                .addPathSegment(id)
                .addPathSegment("photos")
                .addQueryParameter("oauth_token", accessToken)
                .addQueryParameter("v", "20170831")
                .addQueryParameter("limit", "5")
                //.addEncodedQueryParameter("encodedName", "encodedValue")//add encoded query parameters to the URL
                .build();
    }

}