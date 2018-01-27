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
import com.codeapin.newsapp.data.remote.model.ArticlesItem;
import com.codeapin.newsapp.data.remote.model.ArticlesItem;
import com.codeapin.newsapp.data.remote.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    // TODO: (4) Ganti dataset menggunakan List<ArticlesItem>
    private List<ArticlesItem> dataSet = new ArrayList<>();
    // TODO: (23) Buat field variable untuk ReadMoreListener
    private ReadMoreListener readMoreListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArticlesItem articlesItem = dataSet.get(position);

        holder.tvTitle.setText(articlesItem.getTitle());
        holder.tvAuthor.setText(articlesItem.getAuthor());
        holder.tvDescription.setText(articlesItem.getDescription());

        // TODO (25) Panggil method ReadMoreListener.onReadMore saat btnReadMore diklik
        holder.btnReadMore.setOnClickListener(v -> {
            if(readMoreListener != null){
                readMoreListener.onReadMore(articlesItem);
            }
        });


        // TODO: (6) parameter load menggunakan articlesItem.getUrlToImage()
        Glide.with(holder.itemView.getContext())
                .load(articlesItem.getUrlToImage())
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<ArticlesItem> newsItemList){
        this.dataSet = newsItemList;
        notifyDataSetChanged();
    }

    public void clearData(){
        this.dataSet.clear();
        notifyDataSetChanged();
    }

    // TODO: (24) Buat setter untuk field ReadMoreListener
    public void setReadMoreListener(ReadMoreListener readMoreListener){
        this.readMoreListener = readMoreListener;
    }

    // TODO: (21) Definisikan interface ReadMoreListener
    public interface ReadMoreListener{
        // TODO: (22) Definisikan method onReadMore dengan parameter ArticlesItem
        void onReadMore(ArticlesItem articlesItem);
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
