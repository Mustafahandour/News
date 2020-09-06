package com.example.news.data.api;

import com.example.news.data.model.newsRequest.GeneralResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    @GET("top-headlines")
    Call<GeneralResponse> getNewsHeadline(@Query("category") String category,
                                          @Query("apiKey") String apiKey,
                                          @Query("page") int page);

    @GET("top-headlines")
    Call<GeneralResponse> getNewsHeadlineFilter(@Query("category") String category,
                                                @Query("apiKey") String apiKey);

    @GET("everything")
    Call<GeneralResponse> getNewsEverything(@Query("q") String q,
                                            @Query("sortBy") String sortBy,
                                            @Query("apiKey") String apiKey);

    @GET("everything")
    Call<GeneralResponse> getNewsEverythingFilter(@Query("q") String q,
                                                  @Query("apiKey") String apiKey);
}
