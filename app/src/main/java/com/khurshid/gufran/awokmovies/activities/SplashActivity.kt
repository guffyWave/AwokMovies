package com.khurshid.gufran.awokmovies.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.khurshid.gufran.awokmovies.R
import kotlinx.android.synthetic.main.activity_splash.*

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **22 Jan, 2018.**
    Description  :Splash Activity
    All Rights Reserved.
*/

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
            progressBar.visibility = View.GONE
            val mainIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
