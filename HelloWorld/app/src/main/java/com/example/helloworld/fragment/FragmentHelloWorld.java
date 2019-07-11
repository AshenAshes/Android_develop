package com.example.helloworld.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class FragmentHelloWorld extends Fragment {
    GifImageView giv;
    GifDrawable gifDrawable;

    public interface helloInterface{
        void jumpToActivity();
    }
    static helloInterface mInterface;
    public void setInterface(helloInterface mInter) {
        mInterface = mInter;
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("fragHelloWorld:","onCreateView");
        final View view = inflater.inflate(R.layout.fragment_helloworld, container, false);

        giv = view.findViewById(R.id.giv1);
        RadioGroup radGroup = view.findViewById(R.id.radioGroup);
        Button nextBtn = view.findViewById(R.id.nextBtn);
        Button startBtn = view.findViewById(R.id.buttonStart);
        Button resetBtn = view.findViewById(R.id.buttonReset);
        Button stopBtn = view.findViewById(R.id.buttonStop);
        initGiv1();

        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Log.d("checkChange:", "RadioBtn Changed!");
                RadioButton radbtn = view.findViewById(checkedId);
                if(radbtn.getText().toString().equals("Dance")){
                    Log.d("checkChangeResult:",  "Dance");
                    initGiv1();
                }
                else if(radbtn.getText().toString().equals("Pull")){
                    Log.d("checkChangeResult:", "Pull");
                    initGiv2();
                }
                else if(radbtn.getText().toString().equals("Happy")){
                    Log.d("checkChangeResult:", "Happy");
                initGiv3();
                }
                else if(radbtn.getText().toString().equals("Shy")){
                    Log.d("checkChangeResult:", "Shy");
                    initGiv4();
                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterface.jumpToActivity();
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("checkGifCdnChange:", "start");
                gifDrawable.start();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("checkGifCdnChange:", "reset");
                gifDrawable.reset();
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("checkGifCdnChange:", "stop");
                gifDrawable.stop();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initGiv1() {
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.dance);
            giv.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGiv2() {
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.pull);
            giv.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGiv3() {
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.star);
            giv.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGiv4() {
        try {
            gifDrawable = new GifDrawable(getResources(), R.drawable.shy);
            giv.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
