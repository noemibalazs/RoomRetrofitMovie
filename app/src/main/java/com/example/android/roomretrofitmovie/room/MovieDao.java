package com.example.android.roomretrofitmovie.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface MovieDao {

    @Query("SELECT * FROM film")
    LiveData<List<MovieEntity>> loadFavoriteMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovie(MovieEntity entity);

    @Delete
    void deleteMovie(MovieEntity entity);
}
