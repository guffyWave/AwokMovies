package com.khurshid.gufran.awokmovies.communication.retrofit;

import com.khurshid.gufran.awokmovies.communication.retrofit.response.PopularityResult;

import com.khurshid.gufran.awokmovies.communication.retrofit.response.SearchResult;
import com.khurshid.gufran.awokmovies.communication.retrofit.response.TopResult;
import com.khurshid.gufran.awokmovies.entity.MovieDetail;
import com.khurshid.gufran.awokmovies.management.KeyIds;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **21 Jan, 2018.**
    Description  : API methods for Retrofit Client

    All Rights Reserved.
*/

public interface AwokMoviesAPI {

    @GET(KeyIds.SEARCH_MOVIES)
    Observable<SearchResult> searchMovies(@Query("query") String query, @Query("page") int page);

    @GET(KeyIds.GET_POPULAR_MOVIES)
    Observable<PopularityResult> getPopularMovies(@Query("page") int page);

    @GET(KeyIds.GET_TOP_RATED_MOVIES)
    Observable<TopResult> getTopMovies(@Query("page") int page);

    @GET(KeyIds.GET_MOVIE)
    Observable<MovieDetail> getMovie(@Path("id") int id);

}
