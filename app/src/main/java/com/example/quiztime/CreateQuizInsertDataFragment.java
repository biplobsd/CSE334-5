package com.example.quiztime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.quiztime.model.QuizItem;

import java.util.ArrayList;
import java.util.List;

public class CreateQuizInsertDataFragment extends Fragment {
    EditText question, op1, op2, op3, op4;
    String ans;
    Spinner sp;
    Button btn;


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create_quiz_insert_data, container, false);
        question = v.findViewById(R.id.question);
        op1 = v.findViewById(R.id.op1);
        op2 = v.findViewById(R.id.op2);
        op3 = v.findViewById(R.id.op3);
        op4 = v.findViewById(R.id.op4);

        sp = v.findViewById(R.id.spinner);

        btn = v.findViewById(R.id.nextFun);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String questionTxt = question.getText().toString();
                String op1Txt = op1.getText().toString();
                String op2Txt = op2.getText().toString();
                String op3Txt = op3.getText().toString();
                String op4Txt = op4.getText().toString();

                if(questionTxt.length() == 0 || op1Txt.length() == 0 || op2Txt.length() == 0  || op3Txt.length() == 0 || op4Txt.length() == 0){
                    Toast.makeText(getActivity(), "Enter valid quiz", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<String> list=new ArrayList<String>();
                list.add(op1Txt);
                list.add(op2Txt);
                list.add(op3Txt);
                list.add(op4Txt);

                QuizItem quizItem = new QuizItem(questionTxt, Integer.parseInt(sp.getSelectedItem().toString())-1, list);

                CreateQuiz cq = ((CreateQuiz) getActivity());
                assert cq != null;
                cq.changeFragment(quizItem);
                question.setText("");
                op1.setText("");
                op2.setText("");
                op3.setText("");
                op4.setText("");
                sp.setSelection(1);
            }
        });

        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.questionCorrent, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return v;
    }
}