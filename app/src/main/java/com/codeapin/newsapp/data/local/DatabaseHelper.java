package com.codeapin.newsapp.data.local;


import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "news-database";

    private static NewsDatabase INSTANCE;

    public static NewsDatabase getNewsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, NewsDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries() //Jangan lakukan ini
                    .build();
        }
        return INSTANCE;
    }
}
