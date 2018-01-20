package com.khurshid.gufran.awokmovies.exceptions;

/**
 * Created by gufran on 20/1/18.
 */

public class NetworkException extends AwokMovieException {
    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable e) {
        super(message, e);
    }
}
