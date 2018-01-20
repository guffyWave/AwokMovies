package com.khurshid.gufran.awokmovies.communication.retrofit;

import android.content.Context;

import com.khurshid.gufran.awokmovies.R;
import com.khurshid.gufran.awokmovies.management.AwokMovies;
import com.khurshid.gufran.awokmovies.management.KeyIds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by gufran on 20/1/18.
 */

public class AwokMoviesRetrofit {
    private static final int MAX_REQUESTS = 4;
    private static final int CONNECTION_TIMEOUT = 60;// time in seconds
    private static final int READ_TIMEOUT = 60;// time in seconds
    private static final int WRITE_TIMEOUT = 60;// time in seconds
    private static AwokMoviesRetrofit awokMoviesRetrofit;
    private static Retrofit retrofit;

    private AwokMoviesRetrofit() {
    }

    public static synchronized AwokMoviesAPI getAPI() {
        if (awokMoviesRetrofit == null) {
            awokMoviesRetrofit = new AwokMoviesRetrofit();
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(MAX_REQUESTS);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .dispatcher(dispatcher)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();
                            HttpUrl originalHttpUrl = original.url();
                            HttpUrl url = originalHttpUrl.newBuilder()
                                    .addQueryParameter(KeyIds.TEXT_API_KEY, AwokMovies.getAppContext().getString(R.string.server_api_key))
                                    .addQueryParameter(KeyIds.TEXT_LANGUAGE, AwokMovies.getAppContext().getString(R.string.language))
                                    .build();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .url(url);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(AwokMovies.getAppContext().getString(R.string.server_uri))
                    .client(okHttpClient)
                    // converter sequence does matter , a lot !
                    .addConverterFactory(GsonConverterFactory.create())  //for request to json conversion
                    .addConverterFactory(ScalarsConverterFactory.create())//for response to string conversion
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//for Rx Android
                    .build();

        }
        return retrofit.create(AwokMoviesAPI.class);
    }
}
