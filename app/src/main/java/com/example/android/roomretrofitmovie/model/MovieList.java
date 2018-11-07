package com.example.android.roomretrofitmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieList {

    @SerializedName("results")
    private ArrayList<Movie>  movieList = new ArrayList<>();

    public ArrayList<Movie> getMovieList(){
        return movieList;
    }
}
