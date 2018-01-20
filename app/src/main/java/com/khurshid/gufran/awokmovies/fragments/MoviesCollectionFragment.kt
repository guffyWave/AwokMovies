package com.khurshid.gufran.awokmovies.fragments

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.khurshid.gufran.awokmovies.R
import com.khurshid.gufran.awokmovies.adapters.LoadingProxyEntity
import com.khurshid.gufran.awokmovies.adapters.MoviesCollectionAdapter
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
    private var mCurrentPageCount = 1
    private val numberOfColumns = 2
    var mScreenState: State = State.POPULAR
    private var mQuery: String = ""

    enum class State {
        POPULAR, SEARCH, TOP_RATED
    }

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

        moviesRecyclerView.setLayoutManager(GridLayoutManager(activity, numberOfColumns))
        moviesRecyclerView.setHasFixedSize(true)

        mAdapter = MoviesCollectionAdapter(mMovieList, MoviesCollectionAdapter.OnItemClickListener { specie, position ->
            mAdapter.notifyItemChanged(position)
        })

        moviesRecyclerView.adapter = mAdapter

        mPresenter = MoviesPresenter(MoviesDaoImpl(), this)
        showPopularMovies()

        mAdapter.setOnLoadMoreListener {
            mCurrentPageCount++
            when (mScreenState) {
                State.POPULAR -> mPresenter.showPopularMovies(mCurrentPageCount)
                State.SEARCH -> mPresenter.searchMovies(mQuery, mCurrentPageCount)
                State.POPULAR -> mPresenter.showTopMovies(mCurrentPageCount)
            }
        }
    }

    public fun showPopularMovies() {
        mScreenState = State.POPULAR
        mCurrentPageCount = 1
        mMovieList.clear()
        mPresenter.showPopularMovies(mCurrentPageCount)
    }

    public fun searchMovie(query: String) {
        mScreenState = State.SEARCH
        mCurrentPageCount = 1
        mMovieList.clear()
        mQuery = query
        mPresenter.searchMovies(query, mCurrentPageCount)
    }

    public fun showTopRatedMovies() {
        mScreenState = State.TOP_RATED
        mCurrentPageCount = 1
        mMovieList.clear()
        mPresenter.showTopMovies(mCurrentPageCount)
    }

    override fun enListMovie(queryResult: QueryResult?) {
        removeLoadingCard()
        if (queryResult!!.movies != null && queryResult!!.movies.size > 0) {
            synchronized(mMovieList) { mMovieList.addAll(queryResult.movies) }
        } else {
            Toast.makeText(activity, getString(R.string.message_no_more_data), Toast.LENGTH_SHORT).show()
        }
        mAdapter.notifyDataSetChangedManually()
    }

    private fun removeLoadingCard() {
        synchronized(mMovieList) {
            if (mMovieList.size != 0 && (mMovieList.get(mMovieList.size - 1) is LoadingProxyEntity)) {
                mMovieList.removeAt(mMovieList.size - 1)
                mAdapter.notifyDataSetChangedManually()
            }
        }
    }

    override fun showWait() {
        if (mMovieList.size == 0) {
            progressBar.visibility = View.VISIBLE
            moviesRecyclerView.visibility = View.GONE
        } else {
            mMovieList.add(LoadingProxyEntity())
            activity!!.window.decorView.handler.post(Runnable {
                mAdapter.notifyItemInserted(mMovieList.size - 1)
            })
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
        removeLoadingCard()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onStop()
    }
}