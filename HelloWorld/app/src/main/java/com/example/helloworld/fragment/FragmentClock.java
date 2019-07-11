package com.example.helloworld.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;
import com.example.helloworld.widget.clock;

import java.lang.ref.WeakReference;

public class FragmentClock extends Fragment {
    private static final int UPDATE_TIME=1;

    private View mRootView;
    private static clock mClockView;
    private Handler myHandler = new myHandler(this);

    private static class myHandler extends Handler{
        WeakReference<Fragment> mFragmentReference;
        myHandler(Fragment fragment){
            mFragmentReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            final Fragment fragment = mFragmentReference.get();
            if(fragment!=null){
                switch (msg.what){
                    case UPDATE_TIME:
                        mClockView.reDraw();
                        break;
                }
            }
        }
    }

    private class TimeThread extends Thread{
        @Override
        public void run() {
            super.run();
            do{
                try{
                    Thread.sleep(1000);
                    Message msg=new Message();
                    msg.what=UPDATE_TIME;
                    myHandler.sendMessage(msg);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clock, container, false);
        mRootView=view.findViewById(R.id.clockPage);
        mClockView=view.findViewById(R.id.clock);
        TimeThread timeThread=new TimeThread();

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClockView.setmShowClock(!mClockView.isShow());
            }
        });

        timeThread.start();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
        Log.d("FragmentClock:","onDestroy()");
    }
}
