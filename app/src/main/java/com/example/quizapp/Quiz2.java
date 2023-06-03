package com.example.quizapp;

import static com.example.quizapp.Quiz1.DATA_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz2 extends AppCompatActivity {

    int score_quiz1 = 0;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        rg = findViewById(R.id.quizOptions);

        Intent i = getIntent();
        score_quiz1 = i.getIntExtra(DATA_KEY, 0);
    }

    public void finishFun(View view) {
        RadioButton rb = findViewById(rg.getCheckedRadioButtonId());
        String selectdOption = rb.getText().toString();

        int score = 0;
        if (selectdOption.equals("Jackfruit")) {
            score = 5;
        }

        Intent i = new Intent(this, Result.class);
        i.putExtra(DATA_KEY, score + score_quiz1);
        startActivity(i);
    }
}