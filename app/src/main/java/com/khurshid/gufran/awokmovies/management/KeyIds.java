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


    ///search
    //https://api.themoviedb.org/3/search/movie?api_key=1d667dff3c059cf67d84d98d3aa66102&language=en-US&query=apple&page=1&include_adult=false

    ///find -- get a movie deatil
    //https://api.themoviedb.org/3/movie/550?api_key=1d667dff3c059cf67d84d98d3aa66102
    //discover/movie?sort_by=popularity.desc  -- popularity

    //https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=1d667dff3c059cf67d84d98d3aa66102

    //Top Rated

    //https://api.themoviedb.org/3/movie/top_rated?api_key=1d667dff3c059cf67d84d98d3aa66102&language=en-US&page=1


    //http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
    //http://image.tmdb.org/t/p/w185//47pLZ1gr63WaciDfHCpmoiXJlVr.jpg
    //http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg

    //http://image.tmdb.org/t/p/w185///woCxv9kua5uqtRNaiWUBDzrgZnH.jpg Backdrop


}
