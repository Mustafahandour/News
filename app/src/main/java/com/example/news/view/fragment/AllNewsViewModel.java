package com.example.news.view.fragment;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.model.newsRequest.GeneralResponse;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.example.news.data.api.ApiClient.getClient;

public class AllNewsViewModel extends ViewModel {
    MutableLiveData<List<GeneralResponseData>> newsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> news = new MutableLiveData<>();
    private BaseActivity activity;
    public Integer maxPage;


    public void getNews() {
        getClient().getNewsEverything("all", "publishedAt", "11c7514362424d7b8f125be0f8587e75").enqueue(new Callback<GeneralResponse>() {

            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                if (response.isSuccessful()) {
                    maxPage = response.body().getTotalResults();
                    newsMutableLiveData.setValue(response.body().getArticles());
                } else {
                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                news.setValue("errr");

            }
        });
    }

    public void getNews(String q) {
        getClient().getNewsEverythingFilter(q, "11c7514362424d7b8f125be0f8587e75").enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.isSuccessful()) {
                    newsMutableLiveData.setValue(response.body().getArticles());
                } else {
                    Toast.makeText(activity, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                news.setValue("errr");

            }
        });
    }
}
