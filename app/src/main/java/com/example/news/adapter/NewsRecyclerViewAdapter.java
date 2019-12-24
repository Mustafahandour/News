package com.example.news.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.data.model.newsRequest.GeneralResponseData;
import com.example.news.view.activity.BaseActivity;
import com.example.news.view.fragment.NewsDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.news.helper.HelperMethod.onLoadImageFromUrl;
import static com.example.news.helper.HelperMethod.replaceFragment;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {


    private BaseActivity activity;
    List<GeneralResponseData> generalRequestDataList = new ArrayList<>();

    public NewsRecyclerViewAdapter(Activity activity, List<GeneralResponseData> generalRequestDataList) {
        this.activity = (BaseActivity) activity;
        this.generalRequestDataList = generalRequestDataList;
        notifyDataSetChanged();

    }

    public void setList(List<GeneralResponseData> generalRequestDataList) {
        this.generalRequestDataList = generalRequestDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);

    }

    private void setData(ViewHolder holder, int position) {
        holder.itemRvListTvSource.setText(generalRequestDataList.get(position).getSource().getName());
        holder.itemRvListTvAuthor.setText(generalRequestDataList.get(position).getAuthor());
        holder.itemRvListTvTitle.setText(generalRequestDataList.get(position).getTitle());
        holder.itemRvListTvDate.setText(generalRequestDataList.get(position).getPublishedAt());
        onLoadImageFromUrl(holder.itemRvListIvImage, generalRequestDataList.get(position).getUrlToImage(), activity);


    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsFragment newsDetailsFragment = new NewsDetailsFragment();
                newsDetailsFragment.generalResponseData = generalRequestDataList.get(position);
                replaceFragment(newsDetailsFragment, activity.getSupportFragmentManager(), R.id.nav_host_fragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return generalRequestDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_rv_list_tv_source)
        TextView itemRvListTvSource;
        @BindView(R.id.item_rv_list_tv_author)
        TextView itemRvListTvAuthor;
        @BindView(R.id.item_rv_list_tv_title)
        TextView itemRvListTvTitle;
        @BindView(R.id.item_rv_list_iv_image)
        ImageView itemRvListIvImage;
        @BindView(R.id.item_rv_list_tv_date)
        TextView itemRvListTvDate;
        private View view;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
