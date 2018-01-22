package com.khurshid.gufran.awokmovies.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;


/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Utility class

    All Rights Reserved.
*/

public class Utility {
    public static void launchView(Context context, String data) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(data));
        context.startActivity(intent);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
