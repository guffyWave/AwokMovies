package com.khurshid.gufran.awokmovies.util;

import android.app.Activity;

import com.khurshid.gufran.awokmovies.persistence.MovieDatabase;
import com.khurshid.gufran.awokmovies.persistence.MovieMiniEntity;
import com.khurshid.gufran.awokmovies.persistence.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gufran on 21/1/18.
 */

public class DatabaseUtil {

   // @Inject
   //// public MovieRepository movieRepository;
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
        movieDatabase.getMovieRepositoryDao().insert(movie);
    }

    public List<MovieMiniEntity> getMoviesList() {
        return movieDatabase.getMovieRepositoryDao().findAll().getValue();
    }

    public void deleteMovie(MovieMiniEntity movie) {
        movieDatabase.getMovieRepositoryDao().delete(movie);
    }

}
