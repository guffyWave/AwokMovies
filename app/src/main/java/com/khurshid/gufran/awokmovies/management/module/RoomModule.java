package com.khurshid.gufran.awokmovies.management.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.khurshid.gufran.awokmovies.persistence.MovieDataSource;
import com.khurshid.gufran.awokmovies.persistence.MovieDatabase;
import com.khurshid.gufran.awokmovies.persistence.MovieRepository;
import com.khurshid.gufran.awokmovies.persistence.MovieRepositoryDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private MovieDatabase movieDatabase;

    public RoomModule(Application mApplication) {
        movieDatabase = Room.databaseBuilder(mApplication, MovieDatabase.class, "movie-database").build();
    }

    @Singleton
    @Provides
    MovieDatabase providesRoomDatabase() {
        return movieDatabase;
    }

    @Singleton
    @Provides
    MovieRepositoryDao providesProductDao(MovieDatabase demoDatabase) {
        return demoDatabase.getMovieRepositoryDao();
    }

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(MovieRepositoryDao movieRepositoryDao) {
        return new MovieDataSource(movieRepositoryDao);
    }

}
