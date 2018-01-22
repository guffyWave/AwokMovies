package com.khurshid.gufran.awokmovies.dao;

import com.crashlytics.android.Crashlytics;
import com.khurshid.gufran.awokmovies.communication.retrofit.AwokMoviesRetrofit;
import com.khurshid.gufran.awokmovies.communication.retrofit.response.PopularityResult;
import com.khurshid.gufran.awokmovies.communication.retrofit.response.QueryResult;
import com.khurshid.gufran.awokmovies.communication.retrofit.response.SearchResult;
import com.khurshid.gufran.awokmovies.communication.retrofit.response.TopResult;
import com.khurshid.gufran.awokmovies.entity.MovieDetail;
import com.khurshid.gufran.awokmovies.exceptions.AwokMovieException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **21 Jan, 2018.**
    Description  : DAO Implementation

    All Rights Reserved.
*/

public class MoviesDaoImpl implements MoviesDao {
    @Override
    public Subscription searchMovie(String query, int page, final MovieQueryCallback callback) {
        return AwokMoviesRetrofit.getAPI().searchMovies(query, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends SearchResult>>() {
                    @Override
                    public Observable<? extends SearchResult> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<SearchResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        callback.onError(new AwokMovieException("Some Error occured", e));
                    }

                    @Override
                    public void onNext(SearchResult searchResult) {
                        //the server response can be cached too using retrofit cache or diskLRU cache
                        callback.onSuccess(searchResult);
                    }
                });
    }

    @Override
    public Subscription getPopularMovies(int page, final MovieQueryCallback callback) {
        return AwokMoviesRetrofit.getAPI().getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends PopularityResult>>() {
                    @Override
                    public Observable<? extends PopularityResult> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<PopularityResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        callback.onError(new AwokMovieException("Some Error occured", e));
                    }

                    @Override
                    public void onNext(PopularityResult searchResult) {
                        //the server response can be cached too using retrofit cache or diskLRU cache
                        callback.onSuccess(searchResult);
                    }
                });
    }

    @Override
    public Subscription topRatedMovies(int page, final MovieQueryCallback callback) {
        return AwokMoviesRetrofit.getAPI().getTopMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends TopResult>>() {
                    @Override
                    public Observable<? extends TopResult> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<TopResult>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        callback.onError(new AwokMovieException("Some Error occured", e));
                    }

                    @Override
                    public void onNext(TopResult searchResult) {
                        //the server response can be cached too using retrofit cache or diskLRU cache
                        callback.onSuccess(searchResult);
                    }
                });
    }

    @Override
    public Subscription getMovie(int id, final MovieDetailQueryCallback callback) {
        return AwokMoviesRetrofit.getAPI().getMovie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends MovieDetail>>() {
                    @Override
                    public Observable<? extends MovieDetail> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<MovieDetail>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Crashlytics.logException(e);
                        callback.onError(new AwokMovieException("Some Error occured", e));
                    }

                    @Override
                    public void onNext(MovieDetail searchResult) {
                        //the server response can be cached too using retrofit cache or diskLRU cache
                        callback.onSuccess(searchResult);
                    }
                });
    }


    public interface MovieQueryCallback {
        void onSuccess(QueryResult queryResult);

        void onError(AwokMovieException exception);
    }

    public interface MovieDetailQueryCallback {
        void onSuccess(MovieDetail movieDetail);

        void onError(AwokMovieException exception);
    }
}
