package com.example.android.SearchPlaces.POJO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by parth on 12/16/2017.
 */
public class DataList implements Parcelable {

    String placeName;
    Double rating;
    String icon;
    String placeAddress;

    public DataList(String placeName, Double rating, String icon, String placeAddress) {
        this.placeName = placeName;
        this.rating = rating;
        this.icon = icon;
        this.placeAddress = placeAddress;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public Double getRating() {
        return rating;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getIcon() {
        return icon;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.placeName);
        dest.writeValue(this.rating);
        dest.writeString(this.icon);
        dest.writeString(this.placeAddress);
    }

    protected DataList(Parcel in) {
        this.placeName = in.readString();
        this.rating = (Double) in.readValue(Double.class.getClassLoader());
        this.icon = in.readString();
        this.placeAddress = in.readString();
    }

    public static final Parcelable.Creator<DataList> CREATOR = new Parcelable.Creator<DataList>() {
        @Override
        public DataList createFromParcel(Parcel source) {
            return new DataList(source);
        }

        @Override
        public DataList[] newArray(int size) {
            return new DataList[size];
        }
    };
}
