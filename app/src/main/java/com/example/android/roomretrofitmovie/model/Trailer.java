package com.example.android.roomretrofitmovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Trailer implements Parcelable{

    private static final String YOUTUBE_PATH = "https://www.youtube.com/watch?v=";

    @SerializedName("key")
    private String mTrailerImage;

    public Trailer(String trailer){
        mTrailerImage = trailer;
    }

    public String getTrailerImage() {
        return YOUTUBE_PATH + mTrailerImage;
    }

    private Trailer(Parcel in) {
        mTrailerImage = in.readString();
    }

    public final Parcelable.Creator<Trailer> CREATOR = new Parcelable.Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTrailerImage);
    }
}
