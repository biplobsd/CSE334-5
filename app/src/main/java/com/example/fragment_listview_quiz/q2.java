package com.example.fragment_listview_quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class q2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q2, container, false);
        ;
        ((RadioGroup) view.findViewById(R.id.radioOption)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String selectedText = ((Button) view.findViewById(checkedId)).getText().toString();
                ((MainActivity) getActivity()).scoreAdd(1, selectedText, "Au");
            }
        });
        return view;
    }
}