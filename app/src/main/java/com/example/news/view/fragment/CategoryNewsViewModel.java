package com.example.news.view.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.R;
import com.example.news.data.model.newsRequest.GeneralResponse;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.view.activity.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.news.data.api.ApiClient.getClient;

public class CategoryNewsViewModel extends ViewModel {
    public Integer maxPage;
    MutableLiveData<List<GeneralResponseData>> newsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> news = new MutableLiveData<>();
    private BaseActivity activity;

    public void getNews(int page) {
        getClient().getNewsHeadline("general", "11c7514362424d7b8f125be0f8587e75", page).enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                newsMutableLiveData.setValue(response.body().getArticles());
                maxPage = response.body().getTotalResults();


            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

                news.setValue("errr");

            }
        });
    }

    public void getNews(String category) {
        getClient().getNewsHeadlineFilter(category, "11c7514362424d7b8f125be0f8587e75").enqueue(new Callback<GeneralResponse>() {
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
