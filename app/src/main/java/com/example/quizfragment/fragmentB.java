package com.example.quizfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;


public class fragmentB extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radioOptionQ2);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String selectedText = ((Button) view.findViewById(checkedId)).getText().toString();
                ((MainActivity) getActivity()).scoreAdd(1, selectedText, "Google");
            }
        });
        return view;
    }
}