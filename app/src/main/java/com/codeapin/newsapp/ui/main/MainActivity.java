package com.codeapin.newsapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codeapin.newsapp.R;
import com.codeapin.newsapp.data.remote.model.ApiResponse;
import com.codeapin.newsapp.data.remote.model.NewsItem;
import com.codeapin.newsapp.data.remote.service.NewsApiClient;
import com.codeapin.newsapp.data.remote.service.NewsApiService;
import com.codeapin.newsapp.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName(); //MainActivity

    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    private NewsAdapter adapter;
    private Call<ApiResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setupList();
    }

    private void setupList() {
        adapter = new NewsAdapter();

        adapter.setReadMoreListener(articlesItem -> {
            DetailActivity.start(MainActivity.this, articlesItem);
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        rvNews.setLayoutManager(linearLayoutManager);
        rvNews.setAdapter(adapter);

        call = NewsApiClient.getNewsApiService()
                .getTopHeadlinesNews("us");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                adapter.setData(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ", t);
            }
        });
    }

    private void setupView() {
        ButterKnife.bind(this);

    }

    @Deprecated
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
