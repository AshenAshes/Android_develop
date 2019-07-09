package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.helloworld.recyclerview.RecyclerViewActivity;

public class MainActivity extends AppCompatActivity {
    GifImageView giv;
    GifDrawable gifDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        giv = findViewById(R.id.giv1);
        RadioGroup radGroup = findViewById(R.id.radioGroup);
        Button nextBtn = findViewById(R.id.nextBtn);
        initGiv1();
        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Log.d("checkChange:", "RadioBtn Changed!");
                RadioButton radbtn = findViewById(checkedId);
                if(radbtn.getText().toString().equals("Dance")){
                    Log.d("checkChangeResult:", "Dance");
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
                Intent intent=new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
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

    public void startClick(View v) {
        Log.d("checkGifCdnChange:", "start");
        gifDrawable.start();
    }

    public void stopClick(View v) {
        Log.d("checkGifCdnChange:", "stop");
        gifDrawable.stop();
    }

    public void resetClick(View v) {
        Log.d("checkGifCdnChange:", "reset");
        gifDrawable.reset();
    }
}
