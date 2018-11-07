package com.example.android.roomretrofitmovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Review implements Parcelable{

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("content")
    private String mComments;

    public Review(String author, String comments){
        mAuthor=author;
        mComments=comments;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getComments(){
        return mComments;
    }

    private Review(Parcel in) {
        mAuthor = in.readString();
        mComments = in.readString();
    }

    public final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAuthor);
        dest.writeString(mComments);
    }
}
