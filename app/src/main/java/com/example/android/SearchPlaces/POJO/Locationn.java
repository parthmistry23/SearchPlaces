package com.example.android.SearchPlaces.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by parth on 12/14/2017.
 */
public class Locationn {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;


    public Double getLat() {
        return lat;
    }


    public void setLat(Double lat) {
        this.lat = lat;
    }


    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
