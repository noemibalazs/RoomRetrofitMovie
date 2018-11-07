package com.example.android.roomretrofitmovie.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "film")
public class MovieEntity {

    @PrimaryKey()
    private int id;

    private String title;
    private String image;
    private String overview;
    private String userRating;
    private String releaseDate;


    public MovieEntity(int id, String title, String image, String overview, String userRating, String releaseDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public String getOverview(){
        return this.overview;
    }

    public String getUserRating() {
        return this.userRating;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setImage(String  image){
        this.image = image;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }

    public void setUserRating(String userRating){
        this.userRating = userRating;
    }
}
