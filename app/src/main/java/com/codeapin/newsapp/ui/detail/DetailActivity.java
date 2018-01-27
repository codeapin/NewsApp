package com.codeapin.newsapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.codeapin.newsapp.R;
import com.codeapin.newsapp.data.remote.model.ArticlesItem;

public class DetailActivity extends AppCompatActivity {

    //TODO (28) Buat konstan untuk EXTRA_ARTICLE_ITEM
    public static final String EXTRA_ARTICLE_ITEM = "ARTICLE_ITEM";

    // TODO: (29) Buat method untuk menjalankan activity dengan parameter context dan ArticlesItem dan taruh articles item di dalam intent
    public static void start(Context context, ArticlesItem articlesItem) {
        Intent starter = new Intent(context, DetailActivity.class);
        starter.putExtra(EXTRA_ARTICLE_ITEM, articlesItem);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO (31) Ambil articles item dari intent
        ArticlesItem articlesItem = getIntent().getParcelableExtra(EXTRA_ARTICLE_ITEM);

        //TODO (33) Ganti title pada action bar menggunakan title dari articles item dan ganti subtitle dengan url articles item
        getSupportActionBar().setTitle(articlesItem.getTitle());
        getSupportActionBar().setSubtitle(articlesItem.getUrl());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
