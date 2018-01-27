package com.codeapin.newsapp.data.remote.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

// TODO: (1) Buat akun di newsapi.org dan copy file json news yang didapat
// TODO: (2) Install plugin RoboPOJO Generator dan restart android studio
// TODO: (3) Generate Gson dari json yang di dapat dari newsapi.org (centang GSON, getter, setter)
public class ApiResponse {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticlesItem> articles;

    @SerializedName("status")
    private String status;

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setArticles(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    public List<ArticlesItem> getArticles() {
        return articles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}