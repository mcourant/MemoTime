package com.example.maximecours.memotime.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.maximecours.memotime.R;
import com.squareup.picasso.Picasso;

public class AnimateFragment extends Fragment {

    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animate, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = (ImageView) getActivity().findViewById(R.id.imagePicasso);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.example_animator);
                set.setTarget(imageView);
                set.start();
            }
        });

        Picasso.with(getContext()).load("http://cdn.ipernity.com/200/49/32/39814932.461ba68d.640.jpg").into(imageView);

    }

}
