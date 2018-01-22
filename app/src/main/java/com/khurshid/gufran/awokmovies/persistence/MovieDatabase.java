package com.khurshid.gufran.awokmovies.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **21 Jan, 2018.**
    Description  : DB Class


    All Rights Reserved.
*/
@Database(entities = {MovieMiniEntity.class}, version = MovieDatabase.VERSION)
public abstract class MovieDatabase extends RoomDatabase {
    static final int VERSION = 1;
    private static MovieDatabase INSTANCE;

    public abstract MovieRepositoryDao getMovieRepositoryDao();

    public static MovieDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}