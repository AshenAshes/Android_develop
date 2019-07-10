package com.example.helloworld.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;

public class FragmentPropertyAnimation extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propertyanimation, container, false);
        ImageView image= view.findViewById(R.id.imgBenDan);
        Animator animator;
        animator = AnimatorInflater.loadAnimator(view.getContext(),R.animator.breath);
        animator.setTarget(image);
        animator.start();
        return view;
    }
}
