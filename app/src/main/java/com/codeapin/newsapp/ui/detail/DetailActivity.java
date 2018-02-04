package com.codeapin.newsapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.codeapin.newsapp.R;
import com.codeapin.newsapp.data.local.ArticleItemEntity;
import com.codeapin.newsapp.data.local.DatabaseHelper;
import com.codeapin.newsapp.data.local.NewsDatabase;
import com.codeapin.newsapp.data.remote.model.ArticlesItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ARTICLE_ITEM = "ARTICLE_ITEM";
    public static void start(Context context, ArticlesItem articlesItem) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(EXTRA_ARTICLE_ITEM, articlesItem);
        context.startActivity(starter);
    }

    //region BindView
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.web_news)
    WebView webNews;
    @BindView(R.id.scroll_mews)
    NestedScrollView scrollMews;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.progress_news)
    ProgressBar progressNews;
    //endregion

    private NewsDatabase newsDatabase;
    private ArticleItemEntity articleEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        setupView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupView() {
        ButterKnife.bind(this);
        ArticlesItem articlesItem = getIntent().getParcelableExtra(EXTRA_ARTICLE_ITEM);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(articlesItem.getTitle());
        getSupportActionBar().setSubtitle(articlesItem.getUrl());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        WebSettings settings = webNews.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);

        webNews.clearHistory();
        webNews.clearCache(true);
        webNews.setHorizontalScrollBarEnabled(true);
        webNews.setWebViewClient(new WebViewClient());
        webNews.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressNews.setVisibility(View.VISIBLE);
                progressNews.setProgress(newProgress);
                if(progressNews.getMax() == newProgress){
                    progressNews.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webNews.loadUrl(articlesItem.getUrl());

        scrollMews.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(oldScrollY < scrollY){
                    fab.hide();
                }else{
                    fab.show();
                }
            }
        });

        newsDatabase = DatabaseHelper.getNewsDatabase(getApplicationContext());
        articleEntity = newsDatabase.articleItemDao().getByUrl(articlesItem.getUrl());
        if(articleEntity != null && articleEntity.isFavorite()){
            fab.setImageResource(R.drawable.ic_action_favorite);
        }else{
            fab.setImageResource(R.drawable.ic_action_favorite_border);
        }

        fab.setOnClickListener(view -> {
            boolean favorite = false;
            if(articleEntity == null){
                ArticleItemEntity newEntity = new ArticleItemEntity(articlesItem);
                newEntity.setFavorite(true);
                newsDatabase.articleItemDao().insert(newEntity);
                favorite = true;
            }else{
                favorite = !articleEntity.isFavorite();
                articleEntity.setFavorite(favorite);
                newsDatabase.articleItemDao().update(articleEntity);
            }

            if(favorite){
                fab.setImageResource(R.drawable.ic_action_favorite);
            }else{
                fab.setImageResource(R.drawable.ic_action_favorite_border);
            }
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/
        });
    }

}
