package com.example.recyclerview;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//用来存放 数据获取操作 的地方
public class WordRepository {
    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;

    //因为数据库获取的操作需要Context,而Repository又是存放数据库操作的地方，所以需要Context
    public WordRepository(Context context) {
        WordDatabase wordDatabase=WordDatabase.getDatabase(context.getApplicationContext());
        wordDao=wordDatabase.getWordDao();
        allWordsLive=wordDao.getAllWordsLive();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    void insertWords(Word...words){
        new InsertAsyncTask(wordDao).execute(words);
    }
    void updateWords(Word...words){
        new UpdateAsyncTask(wordDao).execute(words);
    }
    void deleteWords(Word...words){
        new DeleteAsyncTask(wordDao).execute(words);
    }
    void deleteAllWords(){ new DeleteAllAsyncTask(wordDao).execute(); }

    //让我们的数据操作转到后台,参数1为输入参数，参数2为后台任务执行进度，参数3为执行结构
    static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao wordDao;
        //通过构造传递过来wordDao
        public InsertAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }
        @Override//这个方法写在后台需要进行的操作
        protected Void doInBackground(Word... words) {
            wordDao.insetWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        //通过构造传递过来wordDao
        public UpdateAsyncTask(WordDao wordDao){this.wordDao=wordDao;}
        @Override//这个方法写在后台需要进行的操作
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{
        private WordDao wordDao;
        //通过构造传递过来wordDao
        public DeleteAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }
        @Override//这个方法写在后台需要进行的操作
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private WordDao wordDao;
        //通过构造传递过来wordDao
        public DeleteAllAsyncTask(WordDao wordDao){
            this.wordDao=wordDao;
        }
        @Override//这个方法写在后台需要进行的操作
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }
}
