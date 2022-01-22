package com.example.recyclerview;
//recyclerview可以回收view中的资源,RecyclerView中每行是一个单元，我们要在res文件中的layout文件夹中为一种单元创建一种布局
//recyclerview需要一个适配器Adapter，这个Adapter用来管理ViewHolder,而ViewHolder来管理样式中的控件
//Database数据的操作不能在主线程中进行，所以我们要让Dao操作继承AsyncTask类
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button,button3;

    WordViewModel wordViewModel;//创建ViewModel
    Switch aSwitch;
    RecyclerView recyclerView;
    MyAdapter myAdapter1,myAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel=new ViewModelProvider(this).get(WordViewModel.class);
        recyclerView=findViewById(R.id.recyclerView);

        myAdapter1=new MyAdapter(false);
        myAdapter2=new MyAdapter(true);
        //设置列表的形式，这个参数表示列表是线性一维的
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter1);
        aSwitch=findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if(b){
                     recyclerView.setAdapter(myAdapter2);
                 }else {
                     recyclerView.setAdapter(myAdapter1);
                 }
            }
        });


        //当数据改变时自动发生的操作，参数1为activity，参数2为观察者
        wordViewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                //
                myAdapter1.setAllWords(words);
                myAdapter2.setAllWords(words);
                //告诉视图，数据发送了改变，重新创建
                myAdapter1.notifyDataSetChanged();
                myAdapter2.notifyDataSetChanged();
            }
        });

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //类名就是表名(Word)
                Word word1=new Word("Hello","你好!");
                Word word2=new Word("World","世界!");
                //wordDao.insetWords(word1,word2);异步后用下面那句就可以实现这里的效果
                wordViewModel.insertWords(word1,word2);
            }
        });

        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordViewModel.deleteAllWords();
            }
        });

    }
}