package com.example.news.data.api;

import com.example.news.data.model.newsRequest.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("top-headlines")
    Call<GeneralResponse> getNews(@Query("country") String country,
                                  @Query("apiKey") String apiKey);

}
