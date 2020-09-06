package com.example.news.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.news.R;
import com.example.news.view.fragment.AllNewsFragment;
import com.example.news.view.fragment.CategoryNewsFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.news.helper.HelperMethod.replaceFragment;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.activity_main_ll_nav_ib_all)
    ImageButton activityMainLlNavIbAll;
    @BindView(R.id.activity_main_ll_nav_ib_area)
    ImageButton activityMainLlNavIbArea;
    @BindView(R.id.activity_main_ll_nav_ib_about)
    ImageButton activityMainLlNavIbAbout;
    @BindView(R.id.adView)
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        CategoryNewsFragment categoryNewsFragment = new CategoryNewsFragment();
        replaceFragment(categoryNewsFragment, getSupportFragmentManager(), R.id.activity_main_fl_container);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
            }
        });


    }


    @OnClick({R.id.activity_main_ll_nav_ib_area, R.id.activity_main_ll_nav_ib_all, R.id.activity_main_ll_nav_ib_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_main_ll_nav_ib_area:
                CategoryNewsFragment categoryNewsFragment = new CategoryNewsFragment();
                replaceFragment(categoryNewsFragment, getSupportFragmentManager(), R.id.activity_main_fl_container);
                break;
            case R.id.activity_main_ll_nav_ib_all:
                AllNewsFragment newsFragment = new AllNewsFragment();
                replaceFragment(newsFragment, getSupportFragmentManager(), R.id.activity_main_fl_container);
                break;
            case R.id.activity_main_ll_nav_ib_about:
                break;
        }
    }
}
