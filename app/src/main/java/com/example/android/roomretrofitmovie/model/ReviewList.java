package com.example.android.roomretrofitmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewList {

    @SerializedName("results")
    private ArrayList<Review> reviewList = new ArrayList<>();

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }
}
