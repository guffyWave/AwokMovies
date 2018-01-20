package com.khurshid.gufran.awokmovies.activities

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.khurshid.gufran.awokmovies.R
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar)
        //supportActionBar?.setDisplayShowTitleEnabled(true)
      //  supportActionBar?.title = "Guffy Toolbar";
        //supportActionBar?.setBackgroundDrawable(transparentColorDrawable)
      //  supportActionBar?.setIcon(R.mipmap.ic_launcher_round)
       // supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mSearchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Do some magic
                Toast.makeText(this@HomeActivity, "Searched " + query, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //Do some magic
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        val item = menu!!.findItem(R.id.action_search)
        mSearchView.setMenuItem(item)
        return true
    }

    override fun onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }
}
