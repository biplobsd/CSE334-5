package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz1 extends AppCompatActivity {
    public static String DATA_KEY = "DATA";
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        rg = findViewById(R.id.quizOptions);

    }

    public void nextQuizFun(View view) {
        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        String selectdOption = rb.getText().toString();

        int score = 0;
        if (selectdOption.equals("8")) {
            score = 5;
        }

        Intent i = new Intent(this, Quiz2.class);
        i.putExtra(DATA_KEY, score);
        startActivity(i);


    }
}