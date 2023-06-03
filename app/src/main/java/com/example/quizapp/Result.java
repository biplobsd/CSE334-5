package com.example.quizapp;

import static com.example.quizapp.Quiz1.DATA_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView finalScoreTV = findViewById(R.id.finalScoreView);

        Intent i = getIntent();
        int finalScore = i.getIntExtra(DATA_KEY, 0);

        finalScoreTV.setText(finalScore + " out of 10");
    }

    public void tryAgainFun(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}