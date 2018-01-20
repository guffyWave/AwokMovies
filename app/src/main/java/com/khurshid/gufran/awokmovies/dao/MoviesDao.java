package com.khurshid.gufran.awokmovies.dao;

import rx.Subscription;

/**
 * Created by gufran on 20/1/18.
 */

public interface MoviesDao {
    Subscription searchMovie(final String query, final int page, final MoviesDaoImpl.MovieQueryCallback callback);

    Subscription getPopularMovies(final int page, final MoviesDaoImpl.MovieQueryCallback callback);

    Subscription topRatedMovies(final int page, final MoviesDaoImpl.MovieQueryCallback callback);

    Subscription getMovie(final int id, final MoviesDaoImpl.MovieDetailQueryCallback callback);

}
