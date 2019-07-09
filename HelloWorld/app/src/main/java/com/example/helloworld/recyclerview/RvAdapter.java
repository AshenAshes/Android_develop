package com.example.helloworld.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter {
    private List<Data> mData=new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void initData(){
        for(int i=1;i<=100;i++){
            Data myData=new Data();
            myData.setRank(i);
            myData.setInf("It's Rank"+i);
            myData.setHotValue(300000-i*200);
            mData.add(myData);
        }
    }
}
