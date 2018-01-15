package com.codeapin.newsapp.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codeapin.newsapp.R;
import com.codeapin.newsapp.data.remote.model.NewsItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    //TODO: (21) Deklarasikan semua view pada layout activity_main
    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO (22) Bind semua view menggunakan ButterKnife
        ButterKnife.bind(this);

        //TODO (24) Buat field NewsAdapter dan isi dataset dengan dummy data
        adapter = new NewsAdapter();
        adapter.setData(getDummyData());

        //TODO (25) Buat sebuah linear layout manager dengan orientation VERTICAL
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        //TODO (26) Gunakan layoutmanager dan adapter yang telah dibuat pada RecyclerView
        rvNews.setLayoutManager(linearLayoutManager);
        rvNews.setAdapter(adapter);
    }

    //TODO (23) Buat method untuk menghasilkan data dummy berupa List<NewsItem>
    public List<NewsItem> getDummyData(){
        List<NewsItem> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            NewsItem newsItem = new NewsItem();
            newsItem.setTitle("Judul "+ String.valueOf(i)); //Judul 1
            newsItem.setAuthor("Penulis "+ String.valueOf(i));
            newsItem.setDescription("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
            newsItem.setUrlCover("http://lorempixel.com/400/200/sports/" + String.valueOf(i));
            result.add(newsItem);
        }
        return result;
    }
}
