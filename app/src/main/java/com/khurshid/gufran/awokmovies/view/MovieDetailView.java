package com.khurshid.gufran.awokmovies.view;

import com.khurshid.gufran.awokmovies.entity.MovieDetail;

/**
 * Created by gufran on 20/1/18.
 */

public interface MovieDetailView {
    void showWait();

    void removeWait();

    void onFailure(String failureMessage);

    void populateMovie(MovieDetail movieDetail);
}
