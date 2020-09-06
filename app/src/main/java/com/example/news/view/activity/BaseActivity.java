package com.example.news.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.news.R;
import com.example.news.view.fragment.BaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;


    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
