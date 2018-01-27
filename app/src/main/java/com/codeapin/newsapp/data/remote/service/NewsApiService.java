package com.codeapin.newsapp.data.remote.service;


import com.codeapin.newsapp.data.remote.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// TODO: (7) Definisikan url newsapi.org menjadi sebuah interface untuk retrofit
public interface NewsApiService {

    @GET("top-headlines")
    Call<ApiResponse> getTopHeadlinesNews(@Query("country") String country);
}
