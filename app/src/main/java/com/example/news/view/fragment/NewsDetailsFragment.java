package com.example.news.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.news.R;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewsDetailsFragment extends BaseFragment {

    public GeneralResponseData generalResponseData;
    @BindView(R.id.fragment_news_details_tv_source)
    TextView fragmentNewsDetailsTvSource;
    @BindView(R.id.fragment_news_details_tv_author)
    TextView fragmentNewsDetailsTvAuthor;
    @BindView(R.id.fragment_news_details_tv_title)
    TextView fragmentNewsDetailsTvTitle;
    @BindView(R.id.fragment_news_details_iv_image)
    ImageView fragmentNewsDetailsIvImage;
    @BindView(R.id.fragment_news_details_tv_description)
    TextView fragmentNewsDetailsTvDescription;
    @BindView(R.id.fragment_news_details_tv_date)
    TextView fragmentNewsDetailsTvDate;
    private Unbinder unbinder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        initFragment();
        unbinder = ButterKnife.bind(this, view);
        getDetails();
        return view;
    }

    private void getDetails() {
        fragmentNewsDetailsTvSource.setText(generalResponseData.getSource().getName());
        fragmentNewsDetailsTvAuthor.setText(generalResponseData.getAuthor());
        HelperMethod.onLoadImageFromUrl(fragmentNewsDetailsIvImage, generalResponseData.getUrlToImage(), getActivity());
        fragmentNewsDetailsTvTitle.setText(generalResponseData.getTitle());
        fragmentNewsDetailsTvDate.setText(generalResponseData.getPublishedAt());
        fragmentNewsDetailsTvDescription.setText(generalResponseData.getDescription());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}