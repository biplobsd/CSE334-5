package com.example.quiztime;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class CreateQuizLenFragment extends Fragment {
    EditText len;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_create_quiz_len, container, false);
        len = v.findViewById(R.id.length);
        Button btn = v.findViewById(R.id.nextFun);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = Integer.parseInt(len.getText().toString());
                CreateQuiz cq = ((CreateQuiz) getActivity());
                cq.setLength(length);
                cq.loadFragment(new CreateQuizInsertDataFragment());
            }
        });
        return v;
    }
}