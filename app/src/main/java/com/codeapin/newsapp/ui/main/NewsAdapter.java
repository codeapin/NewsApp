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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// TODO: (6) Buat adapter yang mewarisi (extends) RecyclerView.Adapter
// TODO: (11) Buat RecyclerView.Adapter menggunakan ViewHolder sebagai TypeParameter (di dalam kurung siku)
// TODO: (12) Implement semua method yang diperlukan
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    // TODO: (13) Buat dataset berupa List<NewsItem>
    private List<NewsItem> dataSet = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO: (14) Definisikan layout dengan menggunakan LayoutInflater
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        // TODO: (15) Definikan ViewHolder yang telah dibuat sebagai return value
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO: (16) Ambil item yang ada pada DataSet sesuai posisi saat ini
        NewsItem newsItem = dataSet.get(position);

        // TODO: (17) Inisiasi view dengan data yang ada pada newsItem
        holder.tvTitle.setText(newsItem.getTitle());
        holder.tvAuthor.setText(newsItem.getAuthor());
        holder.tvDescription.setText(newsItem.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(newsItem.getUrlCover())
                .into(holder.ivCover);
    }

    @Override
    public int getItemCount() {
        // TODO: (18) Jadikan jumlah data pada DataSet sebagai jumlah item yang ada pada RecyclerView
        return dataSet.size();
    }

    // TODO: (19) Buat method untuk mengisi DataSet
    public void setData(List<NewsItem> newsItemList){
        this.dataSet = newsItemList;
        notifyDataSetChanged();
    }

    // TODO: (20) Buat method untuk menghapus semua data pada dataset
    public void clearData(){
        this.dataSet.clear();
        notifyDataSetChanged();
    }

    //TODO: (7) Buat sebuah view holder yang mewarisi (extends) Recycler.ViewHolder
    //TODO: (8) Implement semua method yang diperlukan oleh ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {

        // TODO: (9) Deklarasikan semua view dengan menggunakan anotasi dari butterknife
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

            //TODO: (10) Bind semua view menggunakan ButterKnife
            ButterKnife.bind(this, itemView);
        }
    }
}
