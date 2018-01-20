package com.khurshid.gufran.awokmovies.view;

import com.khurshid.gufran.awokmovies.communication.retrofit.response.QueryResult;

/**
 * Created by gufran on 20/1/18.
 */

public interface MoviesHomeView {
    void showWait();

    void removeWait();

    void onFailure(String failureMessage);

    void enListMovie(QueryResult queryResult);

}
