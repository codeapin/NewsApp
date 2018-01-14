package com.codeapin.newsapp.ui.main;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codeapin.newsapp.R;
import com.codeapin.newsapp.data.remote.model.NewsItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsItem> dataSet = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        //kondisinya
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsItem newsItem = dataSet.get(position);
        holder.tvTitle.setText(newsItem.getTitle());
        holder.tvAuthor.setText(newsItem.getAuthor());
        holder.tvDescription.setText(newsItem.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(newsItem.getUrlCover())
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<NewsItem> newsItemList){
        this.dataSet = newsItemList;
        notifyDataSetChanged();
    }

    public void clearData(){
        this.dataSet = Collections.emptyList();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.btn_read_more)
        Button btnReadMore;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
