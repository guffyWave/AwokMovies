package com.khurshid.gufran.awokmovies.persistence;

import java.util.List;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **21 Jan, 2018.**
    Description  : Presenter


    All Rights Reserved.
*/

public class MovieDataSource implements MovieRepository {
    private MovieRepositoryDao movieRepositoryDao;

    public MovieDataSource(MovieRepositoryDao movieRepositoryDao) {
        this.movieRepositoryDao = movieRepositoryDao;
    }

    @Override
    public MovieMiniEntity findById(int id) {
        return movieRepositoryDao.findById(id);
    }

    @Override
    public List<MovieMiniEntity> findAll() {
        return movieRepositoryDao.findAll();
    }

    @Override
    public long insert(MovieMiniEntity Movie) {
        return movieRepositoryDao.insert(Movie);
    }

    @Override
    public int delete(MovieMiniEntity Movie) {
        return movieRepositoryDao.delete(Movie);
    }
}
