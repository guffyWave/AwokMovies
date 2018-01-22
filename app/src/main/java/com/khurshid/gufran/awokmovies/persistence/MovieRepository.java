package com.khurshid.gufran.awokmovies.persistence;

import java.util.List;

/**
 * Created by gufran on 21/1/18.
 */

public interface MovieRepository {

    MovieMiniEntity findById(int id);

    List<MovieMiniEntity> findAll();

    long insert(MovieMiniEntity movie);

    int delete(MovieMiniEntity movie);
}
