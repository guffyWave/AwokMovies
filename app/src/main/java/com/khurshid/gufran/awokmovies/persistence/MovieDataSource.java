package com.khurshid.gufran.awokmovies.persistence;

import android.arch.lifecycle.LiveData;

import com.khurshid.gufran.awokmovies.entity.Movie;

import java.util.List;

/**
 * Created by gufran on 21/1/18.
 */

public class MovieDataSource implements MovieRepository {
    private MovieRepositoryDao movieRepositoryDao;

    public MovieDataSource(MovieRepositoryDao movieRepositoryDao) {
        this.movieRepositoryDao = movieRepositoryDao;
    }

    @Override
    public LiveData<MovieMiniEntity> findById(int id) {
        return movieRepositoryDao.findById(id);
    }

    @Override
    public LiveData<List<MovieMiniEntity>> findAll() {
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
