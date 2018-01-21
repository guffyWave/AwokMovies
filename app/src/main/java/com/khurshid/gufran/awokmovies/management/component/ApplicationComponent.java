package com.khurshid.gufran.awokmovies.management.component;

import android.app.Application;
import android.content.Context;

import com.khurshid.gufran.awokmovies.management.AwokMovies;
import com.khurshid.gufran.awokmovies.management.module.ApplicationContext;
import com.khurshid.gufran.awokmovies.management.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by gufran on 21/1/18.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AwokMovies awokMovies);

    @ApplicationContext
    Context getContext();

    Application getApplication();

}