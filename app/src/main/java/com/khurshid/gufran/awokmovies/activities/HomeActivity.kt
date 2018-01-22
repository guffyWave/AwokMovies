package com.khurshid.gufran.awokmovies.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import com.khurshid.gufran.awokmovies.R
import com.khurshid.gufran.awokmovies.fragments.MoviesCollectionFragment
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_home.*

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **22 Jan, 2018.**
    Description  :Home Activity
    All Rights Reserved.
*/

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        decorateToolbar()
        mSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                propagateSearchQuery(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        mNavigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem!!.itemId) {
                R.id.navigation_item_popular -> (homeFragment as MoviesCollectionFragment).showPopularMovies()
                R.id.navigation_item_top_rated -> (homeFragment as MoviesCollectionFragment).showTopRatedMovies()
                R.id.navigation_item_search -> mSearchView.showSearch()
            }
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val item = menu!!.findItem(R.id.action_search)
        mSearchView.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> mDrawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (mSearchView.isSearchOpen)
            mSearchView.closeSearch()
        else if ((homeFragment as MoviesCollectionFragment).mScreenState != MoviesCollectionFragment.State.POPULAR)
            (homeFragment as MoviesCollectionFragment).showPopularMovies()
        else
            super.onBackPressed()
    }

    private fun propagateSearchQuery(query: String) {
        (homeFragment as MoviesCollectionFragment).searchMovie(query)
    }

    private fun decorateToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
