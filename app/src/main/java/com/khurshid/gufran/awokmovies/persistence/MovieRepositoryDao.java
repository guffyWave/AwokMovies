package com.khurshid.gufran.awokmovies.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by gufran on 21/1/18.
 */

@Dao
public interface MovieRepositoryDao {
    @Query("SELECT * FROM MovieMiniEntity WHERE id=:id")
    MovieMiniEntity findById(int id);

    @Query("SELECT * FROM MovieMiniEntity")
    List<MovieMiniEntity> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(MovieMiniEntity movieMiniEntity);

    @Delete
    int delete(MovieMiniEntity movieMiniEntity);
}
