package com.example.android.SearchPlaces.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by parth on 12/14/2017.
 */
public class Geometry {
    @SerializedName("location")
    @Expose
    private Locationn location;

    public Locationn getLocation() {
        return location;
    }

    public void setLocation(Locationn location) {
        this.location = location;
    }
}
