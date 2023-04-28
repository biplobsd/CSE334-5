package com.example.quizfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;


public class fragmentB extends Fragment {
    private RadioGroup radioGroup;
    private Communicator communicator;
    private int score = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        communicator = (MainActivity) getActivity();
        radioGroup = view.findViewById(R.id.radioOptionQ2);

        score = getArguments().getInt(MainActivity.BUNDLE_SCORE_KEY);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                pressOption(view, checkedId);
            }
        });
        return view;
    }

    public void pressOption(View view, int selectedId) {
        Button selectedButton = view.findViewById(selectedId);
        String selectedText = selectedButton.getText().toString();
        communicator.passDataCom(isCorrect(selectedText) + score);
    }

    private int isCorrect(String selectedText) {
        switch (selectedText) {
            case "Google":
                return 5;
            default:
                return 0;
        }
    }
}