package com.khurshid.gufran.awokmovies.presenter;

import com.khurshid.gufran.awokmovies.communication.retrofit.response.QueryResult;
import com.khurshid.gufran.awokmovies.dao.MoviesDao;
import com.khurshid.gufran.awokmovies.dao.MoviesDaoImpl;
import com.khurshid.gufran.awokmovies.entity.MovieDetail;
import com.khurshid.gufran.awokmovies.exceptions.AwokMovieException;
import com.khurshid.gufran.awokmovies.view.MovieDetailView;
import com.khurshid.gufran.awokmovies.view.MoviesHomeView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gufran on 20/1/18.
 */

public class MovieDetailPresenter {
    private final MoviesDao mMoviesDao;
    private final MovieDetailView mMoviesDetailView;
    private CompositeSubscription mSubscriptions;

    public MovieDetailPresenter(MoviesDao mMoviesDao, MovieDetailView mMoviesDetailView) {
        this.mMoviesDao = mMoviesDao;
        this.mMoviesDetailView = mMoviesDetailView;
        this.mSubscriptions = new CompositeSubscription();
    }

    public void setMovieDetail(int id) {
        mMoviesDetailView.showWait();
        Subscription subscription = mMoviesDao.getMovie(id, new MoviesDaoImpl.MovieDetailQueryCallback() {
            @Override
            public void onSuccess(MovieDetail movieDetail) {
                mMoviesDetailView.removeWait();
                mMoviesDetailView.populateMovieDetail(movieDetail);
            }

            @Override
            public void onError(AwokMovieException exception) {
                mMoviesDetailView.removeWait();
                mMoviesDetailView.onFailure(exception.getMessage());
            }
        });
        mSubscriptions.add(subscription);
    }

    public void onStop() {
        mSubscriptions.unsubscribe();
    }
}
