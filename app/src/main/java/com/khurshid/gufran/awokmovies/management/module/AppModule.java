package com.khurshid.gufran.awokmovies.management.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : App module with singleton


    All Rights Reserved.
*/


@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

}