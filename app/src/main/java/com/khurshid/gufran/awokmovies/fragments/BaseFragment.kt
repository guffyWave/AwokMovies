package com.khurshid.gufran.awokmovies.fragments

import android.support.v4.app.Fragment
import android.view.View
import com.khurshid.gufran.awokmovies.management.AwokMovies

open abstract class BaseFragment : Fragment() {
    var TAG = AwokMovies.TAG + "_BaseFragment"
    var rootView: View? = null//root view of the fragment
}