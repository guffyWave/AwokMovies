package com.khurshid.gufran.awokmovies.persistence;

import java.util.List;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Repository


    All Rights Reserved.
*/

public interface MovieRepository {

    MovieMiniEntity findById(int id);

    List<MovieMiniEntity> findAll();

    long insert(MovieMiniEntity movie);

    int delete(MovieMiniEntity movie);
}
