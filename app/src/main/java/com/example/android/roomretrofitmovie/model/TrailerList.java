package com.example.android.roomretrofitmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrailerList {

    @SerializedName("results")
    private ArrayList<Trailer> trailerList = new ArrayList<>();

    public ArrayList<Trailer> getTrailerList() {
        return trailerList;
    }


}
