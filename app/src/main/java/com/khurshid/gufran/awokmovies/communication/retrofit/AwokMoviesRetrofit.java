package com.khurshid.gufran.awokmovies.communication.retrofit;

import com.khurshid.gufran.awokmovies.BuildConfig;
import com.khurshid.gufran.awokmovies.management.KeyIds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
    Code Prepared by **Gufran Khurshid**.
    Sr. Android Developer.
    Email Id : gufran.khurshid@gmail.com
    Skype Id : gufran.khurshid
    Date: **21 Jan, 2018.**
    Description  : Singleton for Retrofit Client

    All Rights Reserved.
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

            //Adding the logging interceptor
            //This logging interceptor can also be extended to be stored in a file
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            /*
             Note The server response can be cached too using retrofit cache or diskLRU cache
            * */
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
                                    .addQueryParameter(KeyIds.TEXT_API_KEY, BuildConfig.API_KEY)
                                    .addQueryParameter(KeyIds.TEXT_LANGUAGE, BuildConfig.LANGUAGE)
                                    .build();
                            Request.Builder requestBuilder = original.newBuilder()
                                    .url(url);
                            Request request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    })
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
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
