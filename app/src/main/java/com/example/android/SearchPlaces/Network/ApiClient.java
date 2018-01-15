package com.example.android.SearchPlaces.Network;

import com.example.android.SearchPlaces.POJO.Example;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by parth on 12/13/2017.
 */
public interface ApiClient {

    String BASE_URL = "https://maps.googleapis.com/";

    @GET("maps/api/place/textsearch/json?radius=10000&key=AIzaSyAN8aXGDZHHfyHTV5PWA1KY0BMGaYzkVT8")
    Call<Example> getCityResults(@Query("query") String item,@Query("location") String location);

}
