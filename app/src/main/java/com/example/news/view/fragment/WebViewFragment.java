package com.example.news.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;

import com.example.news.R;
import com.example.news.data.model.newsRequest.GeneralResponseData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WebViewFragment extends BaseFragment {
    public GeneralResponseData generalResponseData;
    @BindView(R.id.fragment_web_wv_web)
    WebView fragmentWebWvWeb;
    String url;
    private Unbinder unbinder;

    public WebViewFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_web, container, false);
        initFragment();
        unbinder = ButterKnife.bind(this, view);
        url = getArguments().getString("url");

        fragmentWebWvWeb.loadUrl(url);
        WebSettings webSettings = fragmentWebWvWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        fragmentWebWvWeb.setWebViewClient(new WebViewClient());


        return view;
    }


}