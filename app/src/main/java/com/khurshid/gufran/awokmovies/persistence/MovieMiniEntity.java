package com.khurshid.gufran.awokmovies.persistence;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;



/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **21 Jan, 2018.**
    Description  : Entity to represent Movie data in mini form


    All Rights Reserved.
*/

@Entity(tableName = "MovieMiniEntity")
public class MovieMiniEntity {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "vote_average")
    private int voteAverage;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "popularity")
    private int popularity;
    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "release_date")
    private String releaseDate;
    @ColumnInfo(name = "vote_count")
    private int voteCount;

    public MovieMiniEntity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
}
