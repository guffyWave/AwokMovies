package com.khurshid.gufran.awokmovies.view;

import com.khurshid.gufran.awokmovies.communication.retrofit.response.QueryResult;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : The Main fragment View

    All Rights Reserved.
*/

public interface MoviesHomeView {
    void showWait();

    void removeWait();

    void onFailure(String failureMessage);

    void enListMovie(QueryResult queryResult);

}
