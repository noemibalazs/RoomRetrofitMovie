package com.example.android.roomretrofitmovie.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase{

    private static final String DATABASE = "movie_database";
    private static final Object LOCK = new Object();
    private static MovieDatabase sdatabase;

    public static MovieDatabase getDatabase(Context context) {

        if (sdatabase == null){
            synchronized (LOCK){
                sdatabase = Room.databaseBuilder(context.getApplicationContext(),
                        MovieDatabase.class, MovieDatabase.DATABASE)
                        .build();
            }
        }
        return sdatabase;
    }

    public abstract MovieDao movieDao();
}
