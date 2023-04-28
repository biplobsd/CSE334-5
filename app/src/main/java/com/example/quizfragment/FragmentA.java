package com.example.quizfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

public class FragmentA extends Fragment {
    private Communicator communicator;
    private RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        communicator = (MainActivity) getActivity();
        radioGroup = view.findViewById(R.id.radioGroup);

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
        communicator.passDataCom(isCorrect(selectedText));
    }

    private int isCorrect(String selectedText) {
        switch (selectedText) {
            case "Cox's Bazar":
                return 5;
            default:
                return 0;
        }

    }

}