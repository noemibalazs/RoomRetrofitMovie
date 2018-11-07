package com.example.android.roomretrofitmovie.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private LiveData<List<MovieEntity>> entities;


    public FavoriteViewModel(@NonNull Application application) {
        super(application);

        MovieDatabase database = MovieDatabase.getDatabase(getApplication());
        entities = database.movieDao().loadFavoriteMovies();

    }

    public LiveData<List<MovieEntity>> getEntities(){
        return entities;
    }
}
