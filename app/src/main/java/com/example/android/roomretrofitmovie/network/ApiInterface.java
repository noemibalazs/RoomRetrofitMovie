package com.example.android.roomretrofitmovie.network;


import com.example.android.roomretrofitmovie.model.MovieList;
import com.example.android.roomretrofitmovie.model.ReviewList;
import com.example.android.roomretrofitmovie.model.TrailerList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top_rated")
    Call<MovieList> getTopRatedList(@Query("api_key") String key);

    @GET("popular")
    Call<MovieList> getPopularList(@Query("api_key") String key);

    @GET("{id}/videos")
    Call<TrailerList> getTrailerList(@Path("id") int id, @Query("api_key") String key);

    @GET("{id}/reviews")
     Call<ReviewList> getReviewList(@Path("id") int id, @Query("api_key") String key);


}
