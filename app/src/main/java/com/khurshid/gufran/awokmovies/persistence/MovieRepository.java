package com.khurshid.gufran.awokmovies.persistence;

import android.arch.lifecycle.LiveData;

import com.khurshid.gufran.awokmovies.entity.Movie;

import java.util.List;

/**
 * Created by gufran on 21/1/18.
 */

public interface MovieRepository {

    LiveData<MovieMiniEntity> findById(int id);

    LiveData<List<MovieMiniEntity>> findAll();

    long insert(MovieMiniEntity movie);

    int delete(MovieMiniEntity movie);
}
