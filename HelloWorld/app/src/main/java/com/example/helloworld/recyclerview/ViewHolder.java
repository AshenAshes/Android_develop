package com.example.helloworld.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView mTextViewRank,mTextViewInf,mTextViewHotValue;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextViewRank=itemView.findViewById(R.id.rank);
        mTextViewInf=itemView.findViewById(R.id.inf);
        mTextViewHotValue=itemView.findViewById(R.id.hotValue);
    }

    public void bind(Data myData){
        if(null==myData) return;
        mTextViewRank.setText(String.valueOf(myData.getRank()));
        mTextViewInf.setText(myData.getInf());
        mTextViewHotValue.setText(String.valueOf(myData.getHotValue()));
    }
}
