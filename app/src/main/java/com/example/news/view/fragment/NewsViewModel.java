package com.example.news.view.fragment;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.model.newsRequest.GeneralResponse;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.view.activity.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.news.data.api.ApiClient.getClient;
import static com.example.news.data.local.SharedPreferencesManger.LoadData;
import static com.example.news.data.local.SharedPreferencesManger.NAME_EGY;
import static com.example.news.data.local.SharedPreferencesManger.NAME_SA;
import static com.example.news.data.local.SharedPreferencesManger.NEWS_NAME;

public class NewsViewModel extends ViewModel {
    MutableLiveData<List<GeneralResponseData>> newsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> news = new MutableLiveData<>();
    private BaseActivity activity;

    public void getData() {
        try {
            if (LoadData(activity, NEWS_NAME).equals(NAME_EGY)) {

                getNews("eg");

            } else if (LoadData(activity, NEWS_NAME).equals(NAME_SA)) {

                getNews("sa");
            } else {

                getNews("us");
            }

        } catch (Exception e) {
        }
    }


    private void getNews(String country) {
        getClient().getNews(country, "11c7514362424d7b8f125be0f8587e75").enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                newsMutableLiveData.setValue(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                news.setValue("errr");

            }
        });
    }
}
