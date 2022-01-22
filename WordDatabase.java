package com.example.recyclerview;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//singleton，只允许生成一个实例
@Database(entities = {Word.class},version = 2,exportSchema = false)//更新数据库时要更改版本号
public abstract class WordDatabase extends RoomDatabase {
    //实现singleton的方法
    private static WordDatabase INSTANCE;
    //synchronized当程序同时申请这个东西时就不会冲突
    static synchronized WordDatabase getDatabase(Context context){
        //如果没有示例就创建一个
        if(INSTANCE ==null){
            //使用context.getApplicationContext()方法可以返回一个最高级的context
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),WordDatabase.class,"word_database")
                    //.fallbackToDestructiveMigration()这种是摧毁再重建式的迁移，我们不用这个
                    .addMigrations(MIGRATION_1_2)//这个表示增加迁移
                    .build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();
    //这个是迁移方案，用静态量来表示，这个表示的是从版本1迁移到版本2
    static final Migration MIGRATION_1_2=new Migration(1,2) {
        @Override//使用.addMigrations需要重写的函数
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE word ADD COLUMN foo_data INTEGER NOT NULL DEFAULT 1");
            //更新数据库的具体操作
        }
    };
}
