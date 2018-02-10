package com.codeapin.newsapp.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ArticleItemEntity.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase{

    public abstract ArticleItemDao articleItemDao();
}
