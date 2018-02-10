package com.codeapin.newsapp.data.local;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.codeapin.newsapp.data.remote.model.ArticlesItem;

import java.util.List;

import retrofit2.http.Url;

@Dao
public interface ArticleItemDao {

    @Query("SELECT * FROM ArticleItemEntity")
    List<ArticleItemEntity> getAll();

    @Query("SELECT * FROM ArticleItemEntity WHERE url = :url LIMIT 1")
    ArticleItemEntity getByUrl(String url);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArticleItemEntity articlesItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ArticleItemEntity> articlesItem);

    @Update
    void update(ArticleItemEntity articlesItem);

    @Delete
    void delete(ArticleItemEntity articlesItem);

    @Delete
    void deleteAll(List<ArticleItemEntity> articleItemEntities);
}

