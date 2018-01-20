package com.khurshid.gufran.awokmovies.management;

import android.app.Application;
import android.content.Context;

/**
 * Created by gufran on 20/1/18.
 */

public class AwokMovies extends Application {
    private static AwokMovies awokMovies;
    public static String TAG = "SPECIES APP";

    @Override
    public void onCreate() {
        super.onCreate();
        awokMovies = AwokMovies.this;
    }

    public AwokMovies getInstance() {
        return awokMovies;
    }

    public static Context getAppContext() {
        return awokMovies.getApplicationContext();
    }
}
