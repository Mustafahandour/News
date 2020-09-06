package com.example.news.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.adapter.NewsRecyclerViewAdapter;
import com.example.news.adapter.NothingSelectedSpinnerAdapter;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.helper.OnEndLess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CategoryNewsFragment extends BaseFragment {

    GeneralResponseData generalResponseData;

    @BindView(R.id.fragment_area_sp_category)
    Spinner fragmentAreaSpCategory;
    @BindView(R.id.fragment_area_ib_search)
    ImageButton fragmentAreaIbSearch;
    @BindView(R.id.fragment_area_rv)
    RecyclerView fragmentAreaRv;
    List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private Unbinder unbinder;
    private LinearLayoutManager linearLayoutManager;
    private NewsRecyclerViewAdapter newsAdapter;
    private CategoryNewsViewModel categoryNewsViewModel;
    private OnEndLess onEndLess;
    private boolean filter = false;
    private ArrayAdapter<CharSequence> adapter;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

    public CategoryNewsFragment() {

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_area_news, container, false);
        initFragment();
        unbinder = ButterKnife.bind(this, view);
        categoryNewsViewModel = ViewModelProviders.of(getActivity()).get(CategoryNewsViewModel.class);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentAreaRv.setLayoutManager(linearLayoutManager);
        spinnerMethod();

        data();
        return view;
    }

    private void data() {
        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= categoryNewsViewModel.maxPage) {
                    if (categoryNewsViewModel.maxPage != 0 && current_page != 1) {
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
        fragmentAreaRv.addOnScrollListener(onEndLess);
        getData(1);
    }

    private void getData(int page) {
        categoryNewsViewModel.getNews(page);

        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), generalResponseDataList);

        fragmentAreaRv.setAdapter(newsRecyclerViewAdapter);
        categoryNewsViewModel.newsMutableLiveData.observe(getActivity(), new Observer<List<GeneralResponseData>>() {
            @Override
            public void onChanged(List<GeneralResponseData> generalResponseData) {
                generalResponseDataList.addAll(generalResponseData);
                newsRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getFilter(int page) {
        filter = true;
        String key = fragmentAreaSpCategory.getSelectedItem().toString();
        categoryNewsViewModel.getNews(key);
        categoryNewsViewModel.newsMutableLiveData.observe(getActivity(), new Observer<List<GeneralResponseData>>() {
            @Override
            public void onChanged(List<GeneralResponseData> generalResponseData) {
                try {
                    if (page == 1) {
                        onEndLess.previousTotal = 0;
                        onEndLess.previous_page = 1;
                        onEndLess.current_page = 1;
                        generalResponseDataList = new ArrayList<>();
                        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(getActivity(), generalResponseDataList);
                        fragmentAreaRv.setAdapter(newsRecyclerViewAdapter);
                    }

                    generalResponseDataList.addAll(generalResponseData);
                    newsRecyclerViewAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                }
            }
        });
    }

    private void spinnerMethod() {


// Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        fragmentAreaSpCategory.setAdapter(new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected, getActivity()));


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


    @OnClick(R.id.fragment_area_ib_search)
    public void onViewClicked() {
        getFilter(1);
    }


}