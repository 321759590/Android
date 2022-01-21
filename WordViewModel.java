package com.example.recyclerview;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;//数据具体操作存放的地方
    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository=new WordRepository(application);
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return wordRepository.getAllWordsLive();
    }
    void insertWords(Word...words){ wordRepository.insertWords(words); }
    void updateWords(Word...words){
        wordRepository.updateWords(words);
    }
    void deleteWords(Word...words){
        wordRepository.deleteWords(words);
    }
    void deleteAllWords(){ wordRepository.deleteAllWords(); }


}
