package com.khurshid.gufran.awokmovies.management.component;

import android.app.Application;

import com.khurshid.gufran.awokmovies.DummyActivity;
import com.khurshid.gufran.awokmovies.management.module.AppModule;
import com.khurshid.gufran.awokmovies.management.module.RoomModule;
import com.khurshid.gufran.awokmovies.persistence.MovieDatabase;
import com.khurshid.gufran.awokmovies.persistence.MovieRepository;
import com.khurshid.gufran.awokmovies.persistence.MovieRepositoryDao;
import com.khurshid.gufran.awokmovies.util.DatabaseUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gufran on 21/1/18.
 */
@Singleton
@Component(dependencies = {}, modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    // void inject(MoviesCollectionFragment moviesCollectionFragment);

    void inject(DatabaseUtil dbUtil);

    MovieRepositoryDao movieRepositoryDao();

    MovieDatabase movieDatabase();

    MovieRepository movieRepository();

    Application application();

}
