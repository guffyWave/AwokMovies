package com.khurshid.gufran.awokmovies.management;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : The application class

    All Rights Reserved.
*/


public class AwokMovies extends Application {
    private static AwokMovies awokMovies;
    public static String TAG = "AWOK_MOVIES";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        awokMovies = AwokMovies.this;
        Stetho.initializeWithDefaults(this);
    }

    public static Context getAppContext() {
        return awokMovies.getApplicationContext();
    }

}
