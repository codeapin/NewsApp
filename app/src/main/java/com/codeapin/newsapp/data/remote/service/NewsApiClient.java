package com.codeapin.newsapp.data.remote.service;


import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApiClient {

    private static NewsApiService INSTANCE;

    public static NewsApiService getNewsApiService() {
        if (INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(new Gson()))
                    .build();

            INSTANCE = retrofit.create(NewsApiService.class);
        }
        return INSTANCE;
    }

    public static OkHttpClient getOkHttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addNetworkInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    HttpUrl originalUrl = originalRequest.url();
                    HttpUrl newUrl = originalUrl.newBuilder()
                            .addQueryParameter("apiKey", "cd3f89ea78aa441d9aafb6f82a313245")
                            .build();
                    Request newRequest = originalRequest.newBuilder()
                            .url(newUrl)
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();
    }
}
