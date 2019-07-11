package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import com.example.helloworld.recyclerview.RecyclerViewActivity;
import com.google.android.material.tabs.TabLayout;

import com.example.helloworld.fragment.FragmentHelloWorld;
import com.example.helloworld.fragment.FragmentAnimation;
import com.example.helloworld.fragment.FragmentClock;

public class MainActivity extends AppCompatActivity implements FragmentHelloWorld.helloInterface {
    private static final int PAGE_COUNT=3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        Log.d("main:0","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.view_pager);
        TabLayout tableLayout = findViewById(R.id.tab_layout);

        final FragmentHelloWorld frag=new FragmentHelloWorld();

        //for test
        if(frag!=null){
            frag.setInterface(this);
            Log.d("main:","new FragmentHelloWorld works well");
        }
        else
            Log.d("main:","new FragmentHelloWorld returns null value");

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position==0)
                    return frag;
                else if(position==1)
                    return new FragmentAnimation();
                else
                    return new FragmentClock();
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }

            @Override
            public CharSequence getPageTitle(int position){
                if(position==0)
                    return "HelloWorld";
                else if(position==1)
                    return "Animation";
                else
                    return "Clock";
            }
//            SpannableString
        });
        tableLayout.setupWithViewPager(pager);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void jumpToActivity() {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }
}
