package com.khurshid.gufran.awokmovies.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.khurshid.gufran.awokmovies.R
import com.khurshid.gufran.awokmovies.dao.MoviesDaoImpl
import com.khurshid.gufran.awokmovies.entity.Genre
import com.khurshid.gufran.awokmovies.entity.Movie
import com.khurshid.gufran.awokmovies.entity.MovieDetail
import com.khurshid.gufran.awokmovies.presenter.MovieDetailPresenter
import com.khurshid.gufran.awokmovies.presenter.MoviesPresenter
import com.khurshid.gufran.awokmovies.util.Utility
import com.khurshid.gufran.awokmovies.view.MovieDetailView
import kotlinx.android.synthetic.main.activity_movie_detail.*

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **22 Jan, 2018.**
    Description  :Movie Detail Activity
    All Rights Reserved.
*/
class MovieDetailActivity : BaseActivity(), MovieDetailView {

    private lateinit var mObtainedMovie: Movie
    private lateinit var mPresenter: MovieDetailPresenter

    companion object {
        fun startActivity(context: FragmentActivity?, bundle: Bundle) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtras(bundle)
            context!!.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        mObtainedMovie = intent.getParcelableExtra("MOVIE")
        populateMovie()


        mPresenter = MovieDetailPresenter(MoviesDaoImpl(), this)
        mPresenter.setMovieDetail(mObtainedMovie.id)
    }

    private fun populateMovie() {
        Glide.with(backdropIV.getContext())
                .load("http://image.tmdb.org/t/p/w300//" + mObtainedMovie.backdropPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                //.placeholder(R.drawable.loading_spinner)
                .into(backdropIV)

        Glide.with(posterIV.getContext())
                .load("http://image.tmdb.org/t/p/w185//" + mObtainedMovie.posterPath)
                //.placeholder(R.drawable.loading_spinner)
                .into(posterIV)

        popularityTV.text = mObtainedMovie.popularity.toInt().toString()
        averageVoteTV.text = mObtainedMovie.voteAverage.toString()
        voteCountTV.text = mObtainedMovie.voteCount.toString()
        movieTitle.text = mObtainedMovie.title

    }


    override fun populateMovieDetail(movieDetail: MovieDetail?) {

        var genres = " "
        for ((index, g: Genre) in movieDetail!!.genres.withIndex()) {
            genres = genres + g.name + (if (index != movieDetail!!.genres.size - 1) "," else " ")
        }
        genreTV.text = genres

        overviewTV.text = movieDetail.overview
        homePageTV.text = movieDetail.homepage
        homePageTV.setOnClickListener { Utility.launchView(this, movieDetail.homepage) }
        budgetTV.text = "Budget : " + movieDetail.budget.toString()
        revenueTV.text = "Revenue : " + movieDetail.revenue.toString()
        originalLanguageTV.text = "Original Language : " + movieDetail.originalLanguage
        adultTV.text = "Section : " + if (movieDetail.adult == true) " Adult " else " Family "
    }

    override fun showWait() {
        loadingDetailsFL.visibility = View.VISIBLE
        moreDetailsLL.visibility = View.GONE
    }

    override fun removeWait() {
        loadingDetailsFL.visibility = View.GONE
        moreDetailsLL.visibility = View.VISIBLE
    }

    override fun onFailure(failureMessage: String?) {
        loadingDetailsFL.visibility = View.GONE
        moreDetailsLL.visibility = View.GONE
        Toast.makeText(this, getString(R.string.message_data_failed), Toast.LENGTH_SHORT).show()
    }


}
