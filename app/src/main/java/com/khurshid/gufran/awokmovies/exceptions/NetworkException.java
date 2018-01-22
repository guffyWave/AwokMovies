package com.khurshid.gufran.awokmovies.exceptions;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Represent Network exception


    All Rights Reserved.
*/


public class NetworkException extends AwokMovieException {
    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable e) {
        super(message, e);
    }
}
