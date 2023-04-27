package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class results extends AppCompatActivity {
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        score = findViewById(R.id.score);
        Intent intent = getIntent();
        Integer q2r = intent.getIntExtra(q2.EXTRA_2_RESULT, 0);
        score.setText("You get " + q2r + " out 10");

    }

    public void tryAgainPressListener(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}