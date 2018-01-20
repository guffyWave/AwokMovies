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

/**
 * Created by gufran on 20/1/18.
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
