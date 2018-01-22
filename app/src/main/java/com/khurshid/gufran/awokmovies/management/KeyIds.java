package com.khurshid.gufran.awokmovies.management;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **30 December, 2017.**

    Description: **Contains important String eg. Endpoints,Preference Key,Bundle Key etc**

    All Rights Reserved.
*/

public interface KeyIds {

    String TEXT_API_KEY = "api_key";
    String TEXT_LANGUAGE="language";

    /*--API DECLARATION--*/
    String GET_MOVIE = "movie/{id}";
    String SEARCH_MOVIES = "search/movie";
    String GET_POPULAR_MOVIES = "discover/movie?sort_by=popularity.desc";
    String GET_TOP_RATED_MOVIES = "movie/top_rated";
}
