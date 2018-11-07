package com.example.android.roomretrofitmovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable{


    private static final String POSTER_URL = "https://image.tmdb.org/t/p/w185";

    @SerializedName("title")
    private String mOriginalTitle;

    @SerializedName("poster_path")
    private String mImageThumbnail;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("vote_average")
    private String mUserRating;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("id")
    private int mId;

    public Movie(String originalTitle, String imageThumbnail, String overView,  String userRating, String releaseDate, int id){
        mOriginalTitle = originalTitle;
        mImageThumbnail = imageThumbnail;
        mOverview = overView;
        mUserRating = userRating;
        mReleaseDate = releaseDate;
        mId = id;
    }


    public String getOriginalTitle(){
        return mOriginalTitle;
    }

    public String getPosterImage(){
        return POSTER_URL + mImageThumbnail;
    }

    public String getMovieOverview(){
        return mOverview;
    }

    public String getUserRating(){
        return  mUserRating;
    }

    public String getReleaseDate(){
        return mReleaseDate;
    }

    public int getId(){
        return  mId;
    }


    private Movie(Parcel in) {
        mOriginalTitle = in.readString();
        mImageThumbnail = in.readString();
        mOverview = in.readString();
        mUserRating = in.readString();
        mReleaseDate = in.readString();
        mId = in.readInt();
    }

    public final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mOriginalTitle);
        dest.writeString(mImageThumbnail);
        dest.writeString(mOverview);
        dest.writeString(mUserRating);
        dest.writeString(mReleaseDate);
        dest.writeInt(mId);
    }
}
