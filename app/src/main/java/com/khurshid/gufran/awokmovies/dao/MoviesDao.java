package com.khurshid.gufran.awokmovies.dao;

import rx.Subscription;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Contract for DAO operations

    All Rights Reserved.
*/

public interface MoviesDao {
    Subscription searchMovie(final String query, final int page, final MoviesDaoImpl.MovieQueryCallback callback);

    Subscription getPopularMovies(final int page, final MoviesDaoImpl.MovieQueryCallback callback);

    Subscription topRatedMovies(final int page, final MoviesDaoImpl.MovieQueryCallback callback);

    Subscription getMovie(final int id, final MoviesDaoImpl.MovieDetailQueryCallback callback);

}
