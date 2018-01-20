package com.khurshid.gufran.awokmovies.activities

import android.support.v7.app.AppCompatActivity
import com.khurshid.gufran.awokmovies.management.AwokMovies

/**
 * Created by gufran on 20/1/18.
 */
open abstract class BaseActivity : AppCompatActivity() {
    var TAG = AwokMovies.TAG + "_BaseActivity"
}