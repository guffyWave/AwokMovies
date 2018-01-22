package com.khurshid.gufran.awokmovies.fragments

import android.support.v4.app.Fragment
import android.view.View
import com.khurshid.gufran.awokmovies.management.AwokMovies

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **20 Jan, 2018.**
    Description  : Contains common attributes required by any fragment

    All Rights Reserved.
*/

open abstract class BaseFragment : Fragment() {
    var TAG = AwokMovies.TAG + "_BaseFragment"
    var rootView: View? = null//root view of the fragment
}