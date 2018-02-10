package com.codeapin.newsapp.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.codeapin.newsapp.data.remote.model.ArticlesItem;
import com.google.gson.annotations.SerializedName;

@Entity
public class ArticleItemEntity {

    private String publishedAt;

    private String author;

    private String urlToImage;

    private String description;

    private String title;

    private boolean favorite;

    @PrimaryKey
    @NonNull
    private String url;

    public ArticleItemEntity(){

    }

    public ArticleItemEntity(ArticlesItem articlesItem) {
        this.publishedAt = articlesItem.getPublishedAt();
        this.author = articlesItem.getAuthor();
        this.urlToImage = articlesItem.getUrlToImage();
        this.description = articlesItem.getDescription();
        this.title = articlesItem.getTitle();
        this.url = articlesItem.getUrl();
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
