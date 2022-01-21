package com.example.recyclerview;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
//singleton，只允许生成一个实例
@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    //实现singleton的方法
    private static WordDatabase INSTANCE;
    //synchronized当程序同时申请这个东西时就不会冲突
    static synchronized WordDatabase getDatabase(Context context){
        //如果没有示例就创建一个
        if(INSTANCE ==null){
            //使用context.getApplicationContext()方法可以返回一个最高级的context
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();
}
