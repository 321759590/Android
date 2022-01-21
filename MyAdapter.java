package com.example.recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//相当于recyclerview的资源管理器，告诉recyclerview什么时候该干什么,<>内表示的是我们用的是自定义的viewHolder
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    //实体的列表，对应我们的滚动列表，一个word对应一行
    List<Word> allWords=new ArrayList<>();//避免空指针，我们先初始化一个空的列表

    boolean useCardView;//判断是否使用CardView

    public MyAdapter(boolean useCardView) {
        this.useCardView = useCardView;
    }

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    //我们创建viewHolder时呼叫的函数
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将我们创建的cell_normal视图传递给MyViewHolder,来创建一个ViewHolder
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View itemView;
        if(useCardView){
            itemView=layoutInflater.inflate(R.layout.cell_card,parent,false);
        }else{
            itemView=layoutInflater.inflate(R.layout.cell_normal,parent,false);
        }

        return new MyViewHolder(itemView);
    }

    //我们的viewHolder与recyclerView绑定时呼叫的函数,参数2为 List<Word>的下标,相当于列表里的第几行
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Word word=allWords.get(position);
        holder.textViewNumber.setText(String.valueOf(position));
        holder.textViewEnglish.setText(word.getWord());
        holder.textViewChinese.setText(word.getChineseMeaning());
        //设置点击后跳转到网址
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //装网址的变量
                Uri uri=Uri.parse("https://m.youdao.com/dict?le=eng&q="+holder.textViewEnglish.getText());
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(uri);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    //返回列表数据总的个数
    @Override
    public int getItemCount() {
        return allWords.size();
    }

    //内部类
    //viewHolder用来管理 滚动列表的 一行的样式,内部类一般加static防止内存泄漏
     static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewNumber,textViewEnglish,textViewChinese;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNumber=itemView.findViewById(R.id.textViewNumber);
            textViewChinese=itemView.findViewById(R.id.textViewChinese);
            textViewEnglish=itemView.findViewById(R.id.textViewEnglish);
        }
    }
}
