package com.example.news.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.adapter.NewsRecyclerViewAdapter;
import com.example.news.data.local.SharedPreferencesManger;
import com.example.news.data.model.newsRequest.GeneralResponse;
import com.example.news.data.model.newsRequest.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.news.data.api.ApiClient.getClient;
import static com.example.news.data.local.SharedPreferencesManger.LoadData;
import static com.example.news.data.local.SharedPreferencesManger.NAME_EGY;
import static com.example.news.data.local.SharedPreferencesManger.NAME_SA;
import static com.example.news.data.local.SharedPreferencesManger.NAME_US;
import static com.example.news.data.local.SharedPreferencesManger.NEWS_NAME;

public class NewsFragment extends BaseFragment {

    @BindView(R.id.news_fragment_rv)
    RecyclerView newsFragmentRv;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private NewsRecyclerViewAdapter newsAdapter;
    List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private NewsViewModel newsViewModel;

    public NewsFragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initFragment();
        unbinder = ButterKnife.bind(this, view);
        newsViewModel = ViewModelProviders.of(getActivity()).get(NewsViewModel.class);

        newsViewModel.getData();


        NewsRecyclerViewAdapter newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), generalResponseDataList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        newsFragmentRv.setLayoutManager(linearLayoutManager);
        newsFragmentRv.setAdapter(newsRecyclerViewAdapter);
        newsViewModel.newsMutableLiveData.observe(getActivity(), new Observer<List<GeneralResponseData>>() {
            @Override
            public void onChanged(List<GeneralResponseData> generalResponseData) {
                newsRecyclerViewAdapter.setList(generalResponseData);
            }
        });


        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}