package com.khurshid.gufran.awokmovies.util;

import android.app.Activity;
import android.os.AsyncTask;

import com.khurshid.gufran.awokmovies.persistence.MovieDatabase;
import com.khurshid.gufran.awokmovies.persistence.MovieMiniEntity;

import java.util.List;

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
        // movieDatabase.getMovieRepositoryDao().insert(movie);
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
