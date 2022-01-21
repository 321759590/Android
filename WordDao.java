package com.example.recyclerview;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao //Database access object  相当于是表的操作合集
public interface WordDao {
    @Insert//用于把关键字和值传递给编译器，更精确低控制编译器的动作，告诉编译器这是一个insert的函数
    void insetWords(Word...words);

    @Update
    void updateWords(Word...words);

    @Delete
    void deleteWords(Word...words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC")
        //List<Word> getAllWords();
        //把上面那个写成LiveData的形式,这样就能自动刷新了，这个不需要继承后台任务的类，系统默认放在后台执行
    LiveData<List<Word>> getAllWordsLive();
}
