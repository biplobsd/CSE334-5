package com.example.quizfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class fragmentC extends Fragment {
    private TextView scoreView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_c, container, false);
        int score = getArguments().getInt(MainActivity.BUNDLE_SCORE_KEY);
        scoreView = view.findViewById(R.id.score);
        scoreView.setText("You get " + score + " out of 10");

        return view;
    }
}