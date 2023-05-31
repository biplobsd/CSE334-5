package com.example.quiztime;

import static com.example.quiztime.QuizLoad.QUIZ_LOAD_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText qid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qid = findViewById(R.id.quizID);
    }

    public void loginFun(View view) {
        startActivity(new Intent(this, Login.class));
    }

    public void registerFun(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void startQuiz(View view) {
        Intent i = new Intent(this, QuizLoad.class);
        i.putExtra(QUIZ_LOAD_ID, Integer.parseInt(qid.getText().toString()));
        startActivity(i);
    }
}