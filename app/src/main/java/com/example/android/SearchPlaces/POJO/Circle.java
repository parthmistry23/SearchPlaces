package com.example.android.SearchPlaces.POJO;

/**
 * Created by parth on 12/15/2017.
 */
public class Circle {

    String placeName;
    Double rating;

    public Circle(String placeName, Double rating) {
        this.placeName = placeName;
        this.rating = rating;
    }
    public Circle(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Double getRating() {
        return rating;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
