package com.khurshid.gufran.awokmovies.view;

import com.khurshid.gufran.awokmovies.entity.MovieDetail;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : The detail page View

    All Rights Reserved.
*/

public interface MovieDetailView {
    void showWait();

    void removeWait();

    void onFailure(String failureMessage);

    void populateMovieDetail(MovieDetail movieDetail);
}
