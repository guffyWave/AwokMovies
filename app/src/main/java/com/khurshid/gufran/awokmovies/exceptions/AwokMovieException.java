package com.khurshid.gufran.awokmovies.exceptions;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Generic Awok Exception

    All Rights Reserved.
*/

public class AwokMovieException extends Exception {
    public AwokMovieException(String message) {
        super(message);
    }

    public AwokMovieException(String message, Throwable e) {
        super(message, e);
    }
}
