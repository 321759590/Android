package com.example.recyclerview;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity//实体，相当于是一张表里的一行数据,List<Word>相当于是一张表
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="english_word")//字段名称
    private String word;
    @ColumnInfo(name="chinese_meaning")
    private String chineseMeaning;

    //构造，id自动生成，不用做参数
    public Word(String word, String chineseMeaning) {
        this.word = word;
        this.chineseMeaning = chineseMeaning;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public String getChineseMeaning() {
        return chineseMeaning;
    }
    public void setChineseMeaning(String chineseMeaning) {
        this.chineseMeaning = chineseMeaning;
    }
}
