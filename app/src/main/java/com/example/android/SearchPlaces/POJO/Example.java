package com.example.android.SearchPlaces.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by parth on 12/13/2017.
 */
public class Example {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions= new ArrayList<>();
    @SerializedName("next_page_token")
    @Expose
    private String nextPageToken;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<Result>();
    @SerializedName("status")
    @Expose
    private String status;

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public List<Result> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }
}
