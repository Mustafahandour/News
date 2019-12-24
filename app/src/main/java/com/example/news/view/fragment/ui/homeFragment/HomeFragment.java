package com.example.news.view.fragment.ui.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.news.R;
import com.example.news.data.local.SharedPreferencesManger;
import com.example.news.helper.HelperMethod;
import com.example.news.view.fragment.NewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.news.data.local.SharedPreferencesManger.NAME_EGY;
import static com.example.news.data.local.SharedPreferencesManger.NAME_SA;
import static com.example.news.data.local.SharedPreferencesManger.NAME_US;
import static com.example.news.data.local.SharedPreferencesManger.saveUserType;
import static com.example.news.helper.HelperMethod.replaceFragment;

public class HomeFragment extends Fragment {


    @BindView(R.id.home_fragment_iv_egypt)
    ImageView homeFragmentIvEgypt;
    @BindView(R.id.home_fragment_iv_saudi_arabia)
    ImageView homeFragmentIvSaudiArabia;
    @BindView(R.id.home_fragment_iv_us)
    ImageView homeFragmentIvUs;
    private Unbinder unbinder;
    private Bundle bundle;
    private NewsFragment newsFragment;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    unbinder.unbind();
    }

    @OnClick({R.id.home_fragment_iv_egypt, R.id.home_fragment_iv_saudi_arabia, R.id.home_fragment_iv_us})
    public void onViewClicked(View view) {
        newsFragment = new NewsFragment();
        saveUserType(getActivity(),null);

        switch (view.getId()) {
            case R.id.home_fragment_iv_egypt:
                saveUserType(getActivity(),NAME_EGY);
                replaceFragment(newsFragment,getActivity().getSupportFragmentManager(),R.id.nav_host_fragment);
                break;
            case R.id.home_fragment_iv_saudi_arabia:
                saveUserType(getActivity(),NAME_SA);

                replaceFragment(newsFragment,getActivity().getSupportFragmentManager(),R.id.nav_host_fragment);
                break;
            case R.id.home_fragment_iv_us:
                saveUserType(getActivity(),NAME_US);

                replaceFragment(newsFragment,getActivity().getSupportFragmentManager(),R.id.nav_host_fragment);
                break;
        }
    }
}




