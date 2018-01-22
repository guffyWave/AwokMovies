package com.khurshid.gufran.awokmovies.util;

import android.app.Activity;
import android.os.AsyncTask;

import com.khurshid.gufran.awokmovies.persistence.MovieDatabase;
import com.khurshid.gufran.awokmovies.persistence.MovieMiniEntity;
import com.khurshid.gufran.awokmovies.persistence.MovieRepository;

import java.util.List;

import javax.inject.Inject;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Database Utility class

    Note: Room with Dagger was not getting compiled hence, I have injected the object but not
    initialized and used Dagger.

    All Rights Reserved.
*/
public class DatabaseUtil {

    @Inject
    public MovieRepository movieRepository;
    MovieDatabase movieDatabase;

    public DatabaseUtil(Activity activity) {
//        DaggerAppComponent.builder()
//                .appModule(new AppModule(activity.getApplication()))
//                .roomModule(new RoomModule(activity.getApplication()))
//                .build()
//                .inject(this);

        movieDatabase = MovieDatabase.getAppDatabase(activity);
    }

    public void insertMovie(MovieMiniEntity movie) {
        new InsertAsyncTask().execute(movie);
    }

    public List<MovieMiniEntity> getMoviesList() {
        return movieDatabase.getMovieRepositoryDao().findAll();
    }

    public void deleteMovie(MovieMiniEntity movie) {
        movieDatabase.getMovieRepositoryDao().delete(movie);
    }

    class InsertAsyncTask extends AsyncTask<MovieMiniEntity, Void, Void> {
        @Override
        protected Void doInBackground(MovieMiniEntity... movieMiniEntities) {
            movieDatabase.getMovieRepositoryDao().insert(movieMiniEntities[0]);
            return null;
        }
    }



}
