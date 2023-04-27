package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class q2 extends AppCompatActivity {
    RadioGroup radioGroup;
    public  static  final String EXTRA_2_RESULT = "com.example.quizapp.extra.2.result";
    Integer q1r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q2);
        radioGroup = findViewById(R.id.radioOptionQ2);
        Intent intent = getIntent();
        q1r = intent.getIntExtra(MainActivity.EXTRA_1_RESULT, 0);
    }

    public void nextPressListener(View view) {
        Intent intent = new Intent(this, results.class);
        intent.putExtra(EXTRA_2_RESULT, isCorrect()+q1r);
        startActivity(intent);
    }

    int isCorrect(){
        int selectedID = radioGroup.getCheckedRadioButtonId();
        Button selectedButton = findViewById(selectedID);
        String selectedText = selectedButton.getText().toString();
        switch (selectedText){
            case "Google":
                return 5;
            default:
                return 0;
        }
    }

    public void prePressListener(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}