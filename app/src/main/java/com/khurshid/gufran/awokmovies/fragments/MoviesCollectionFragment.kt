package com.khurshid.gufran.awokmovies.fragments

import android.R.attr.data
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import com.khurshid.gufran.awokmovies.R
import com.khurshid.gufran.awokmovies.adapters.LoadingProxyEntity
import com.khurshid.gufran.awokmovies.adapters.MoviesCollectionAdapter
import com.khurshid.gufran.awokmovies.communication.retrofit.response.PopularityResult
import com.khurshid.gufran.awokmovies.communication.retrofit.response.QueryResult
import com.khurshid.gufran.awokmovies.dao.MoviesDaoImpl
import com.khurshid.gufran.awokmovies.presenter.MoviesPresenter
import com.khurshid.gufran.awokmovies.view.MoviesHomeView
import kotlinx.android.synthetic.main.fragment_movies_collection.*


/**
 * Created by gufran on 20/1/18.
 */
class MoviesCollectionFragment() : BaseFragment(), MoviesHomeView {

    private lateinit var mPresenter: MoviesPresenter
    private lateinit var mAdapter: MoviesCollectionAdapter
    private lateinit var mMovieList: MutableList<Any>
    private var mNextPageCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMovieList = mutableListOf()
        retainInstance = true // this will make sure fragment will not get recreated on device configuration changes e.g. screen rotation , etc
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater?.inflate(R.layout.fragment_movies_collection,
                container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val numberOfColumns = 2
        moviesRecyclerView.setLayoutManager(GridLayoutManager(activity, numberOfColumns))


        mAdapter = MoviesCollectionAdapter(mMovieList, MoviesCollectionAdapter.OnItemClickListener { specie, position ->

            mAdapter.notifyItemChanged(position)
        })

        moviesRecyclerView.adapter = mAdapter

        mPresenter = MoviesPresenter(MoviesDaoImpl(), this)
        mPresenter.showPopularMovies(mNextPageCount)

        mAdapter.setOnLoadMoreListener {
            mPresenter.showPopularMovies(++mNextPageCount)
        }

    }

    override fun showWait() {
        if (mMovieList.size == 0) {
            progressBar.visibility = View.VISIBLE
            moviesRecyclerView.visibility = View.GONE
        } else {
            mMovieList.add(LoadingProxyEntity())
            mAdapter.notifyItemInserted(mMovieList.size - 1)
            progressBar.visibility = View.GONE
            moviesRecyclerView.visibility = View.VISIBLE
        }
    }

    override fun removeWait() {
        progressBar.visibility = View.GONE
        moviesRecyclerView.visibility = View.VISIBLE
    }

    override fun onFailure(failureMessage: String?) {
        Toast.makeText(activity, getString(R.string.message_data_failed), Toast.LENGTH_SHORT).show()
        removeLoadAtBottom()
    }

    override fun enListMovie(queryResult: QueryResult?) {

        removeLoadAtBottom()

        var popularityResult:PopularityResult= queryResult as PopularityResult

       // popularityResult.
        if (popularityResult.movies != null && popularityResult.movies.size > 0) {
            synchronized(mMovieList) { mMovieList.addAll(popularityResult.movies ) }
        } else {
            Toast.makeText(activity, getString(R.string.message_no_more_data), Toast.LENGTH_SHORT).show()
        }

        mAdapter.notifyDataSetChangedManually()
    }
    private fun removeLoadAtBottom() {
        synchronized(mMovieList) {
            if (mMovieList.size != 0 && (mMovieList.get(mMovieList.size - 1) is LoadingProxyEntity)) {
                mMovieList.removeAt(mMovieList.size - 1)
                mAdapter.notifyDataSetChangedManually()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onStop()
    }
}