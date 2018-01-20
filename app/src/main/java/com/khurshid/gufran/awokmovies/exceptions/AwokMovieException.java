package com.khurshid.gufran.awokmovies.exceptions;

/**
 * Created by gufran on 20/1/18.
 */

public class AwokMovieException extends Exception {
    public AwokMovieException(String message) {
        super(message);
    }

    public AwokMovieException(String message, Throwable e) {
        super(message, e);
    }
}
