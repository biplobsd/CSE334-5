package com.example.quiztime;

import static com.example.quiztime.QuizLoad.QUIZ_LOAD_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        String quizId = qid.getText().toString().trim();
        String errorString = "Enter valid quizID";
        if(quizId.length() == 0){
            Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();
            qid.setError(errorString);
            return;
        }
        Integer quizIDNo;
        try {
            quizIDNo = Integer.parseInt(quizId);
        }catch (Exception e){
            Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();
            qid.setError(errorString);
            return;
        }

        Intent i = new Intent(this, EnterName.class);

        i.putExtra(QUIZ_LOAD_ID, quizIDNo);
        startActivity(i);
    }
}