package com.example.news.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.adapter.NewsRecyclerViewAdapter;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.helper.OnEndLess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AllNewsFragment extends BaseFragment {


    @BindView(R.id.fragment_all_news_et_key)
    EditText fragmentAllNewsEtKey;
    @BindView(R.id.fragment_all_news_ib_search)
    ImageButton fragmentAllNewsIbSearch;
    @BindView(R.id.fragment_all_news_rc)
    RecyclerView fragmentAllNewsRc;
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private AllNewsViewModel newsViewModel;
    private boolean filter = false;
    private String key;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private OnEndLess onEndLess;

    public AllNewsFragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initFragment();
        unbinder = ButterKnife.bind(this, view);
        newsViewModel = ViewModelProviders.of(getActivity()).get(AllNewsViewModel.class);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentAllNewsRc.setLayoutManager(linearLayoutManager);


        data();

        return view;
    }

    private void data() {
        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= newsViewModel.maxPage) {
                    if (newsViewModel.maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = onEndLess.current_page;
                        if (filter) {
                            getFilter(current_page);
                        } else {
                            getData(current_page);
                        }

                    }
                    onEndLess.current_page = onEndLess.previous_page;

                } else {
                    onEndLess.current_page = onEndLess.previous_page;

                }
            }
        };
        fragmentAllNewsRc.addOnScrollListener(onEndLess);
        getData(1);
    }

    private void getData(int page) {
        newsViewModel.getNews();
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), generalResponseDataList);
        fragmentAllNewsRc.setAdapter(newsRecyclerViewAdapter);
        newsViewModel.newsMutableLiveData.observe(getActivity(), new Observer<List<GeneralResponseData>>() {
            @Override
            public void onChanged(List<GeneralResponseData> generalResponseData) {
                generalResponseDataList.addAll(generalResponseData);
                newsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getFilter(int page) {
        key = fragmentAllNewsEtKey.getText().toString().trim();

        newsViewModel.getNews(key);

        newsViewModel.newsMutableLiveData.observe(getActivity(), new Observer<List<GeneralResponseData>>() {
            @Override
            public void onChanged(List<GeneralResponseData> generalResponseData) {
                try {
                    if (page == 1) {
                        onEndLess.previousTotal = 0;
                        onEndLess.previous_page = 1;
                        onEndLess.current_page = 1;
                        generalResponseDataList = new ArrayList<>();
                        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), generalResponseDataList);
                        fragmentAllNewsRc.setAdapter(newsRecyclerViewAdapter);
                    }

                    generalResponseDataList.addAll(generalResponseData);
                    newsRecyclerViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onBack() {
        getActivity().finish();
    }


    @OnClick(R.id.fragment_all_news_ib_search)
    public void onViewClicked() {
        getFilter(1);
    }


}