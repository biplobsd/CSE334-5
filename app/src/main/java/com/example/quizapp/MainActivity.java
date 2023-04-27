package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    public  static  final String EXTRA_1_RESULT = "com.example.quizapp.extra.1.result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radioOption);
    }

    public void nextPressListener(View view) {
        Intent intent = new Intent(this, q2.class);
        intent.putExtra(EXTRA_1_RESULT, isCorrect());
        startActivity(intent);
    }

    int isCorrect(){
        int selectedID = radioGroup.getCheckedRadioButtonId();
        Button selectedButton = findViewById(selectedID);
        String selectedText = selectedButton.getText().toString();
        switch (selectedText){
            case "Cox's Bazar":
                return 5;
            default:
                return 0;
        }
    }
}