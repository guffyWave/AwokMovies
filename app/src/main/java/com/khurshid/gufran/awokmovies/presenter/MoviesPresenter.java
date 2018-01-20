package com.khurshid.gufran.awokmovies.presenter;

import com.khurshid.gufran.awokmovies.communication.retrofit.response.QueryResult;
import com.khurshid.gufran.awokmovies.dao.MoviesDao;
import com.khurshid.gufran.awokmovies.dao.MoviesDaoImpl;
import com.khurshid.gufran.awokmovies.exceptions.AwokMovieException;
import com.khurshid.gufran.awokmovies.view.MoviesHomeView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gufran on 20/1/18.
 */

public class MoviesPresenter {
    private final MoviesDao mMoviesDao;
    private final MoviesHomeView mMoviesHomeView;
    private CompositeSubscription mSubscriptions;

    public MoviesPresenter(MoviesDao mMoviesDao, MoviesHomeView mMoviesHomeView) {
        this.mMoviesDao = mMoviesDao;
        this.mMoviesHomeView = mMoviesHomeView;
        this.mSubscriptions = new CompositeSubscription();
    }

    public void showPopularMovies(int page) {
        mMoviesHomeView.showWait();
        Subscription subscription = mMoviesDao.getPopularMovies(page, new MoviesDaoImpl.MovieQueryCallback() {
            @Override
            public void onSuccess(QueryResult queryResult) {
                mMoviesHomeView.removeWait();
                mMoviesHomeView.enListMovie(queryResult);
            }

            @Override
            public void onError(AwokMovieException exception) {
                mMoviesHomeView.removeWait();
                mMoviesHomeView.onFailure(exception.getMessage());
            }
        });
        mSubscriptions.add(subscription);
    }

    public void showTopMovies(int page) {
        mMoviesHomeView.showWait();
        Subscription subscription = mMoviesDao.topRatedMovies(page, new MoviesDaoImpl.MovieQueryCallback() {
            @Override
            public void onSuccess(QueryResult queryResult) {
                mMoviesHomeView.removeWait();
                mMoviesHomeView.enListMovie(queryResult);
            }

            @Override
            public void onError(AwokMovieException exception) {
                mMoviesHomeView.removeWait();
                mMoviesHomeView.onFailure(exception.getMessage());
            }
        });
        mSubscriptions.add(subscription);
    }

    public void searchMovies(String query,int page) {
        mMoviesHomeView.showWait();
        Subscription subscription = mMoviesDao.searchMovie(query,page, new MoviesDaoImpl.MovieQueryCallback() {
            @Override
            public void onSuccess(QueryResult queryResult) {
                mMoviesHomeView.removeWait();
                mMoviesHomeView.enListMovie(queryResult);
            }

            @Override
            public void onError(AwokMovieException exception) {
                mMoviesHomeView.removeWait();
                mMoviesHomeView.onFailure(exception.getMessage());
            }
        });
        mSubscriptions.add(subscription);
    }

    public void onStop() {
        mSubscriptions.unsubscribe();
    }

}
