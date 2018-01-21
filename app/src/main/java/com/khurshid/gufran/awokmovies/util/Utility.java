package com.khurshid.gufran.awokmovies.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;

/**
 * Created by gufran on 21/1/18.
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
