package com.example.quiztime;

import static com.example.quiztime.QuizLoad.QUIZ_LOAD_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EnterName extends AppCompatActivity {
    EditText name;
    Integer quizID;
    public static String USER_NAME = "User_Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        quizID = getIntent().getIntExtra(QUIZ_LOAD_ID, 0);
        name = findViewById(R.id.name);
    }

    public void nextFun(View view) {
        String nameString = name.getText().toString().trim();
        if(nameString.length() == 0){
            Toast.makeText(this, "Enter any name", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(this, QuizLoad.class);
        i.putExtra(QUIZ_LOAD_ID, quizID);
        i.putExtra(USER_NAME, nameString);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}