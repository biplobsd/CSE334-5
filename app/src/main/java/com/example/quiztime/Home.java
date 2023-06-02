package com.example.quiztime;

import static com.example.quiztime.Login.USER_INFO_KEY;
import static com.example.quiztime.QuizLoad.QUIZ_LOAD_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    EditText quizId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView username = findViewById(R.id.email);

        Intent i = getIntent();
        username.setText(i.getStringExtra(USER_INFO_KEY));
        quizId = findViewById(R.id.quizId);
    }

    public void startQuizFun(View view) {
        String quizIdString = quizId.getText().toString().trim();
        Intent i = new Intent(this, QuizLoad.class);
        i.putExtra(QUIZ_LOAD_ID, Integer.parseInt(quizIdString));
        startActivity(i);
    }

    public void createQuizFun(View view) {
        startActivity(new Intent(this, CreateQuiz.class));
    }

    public void deleteFun(View view) {
        Toast.makeText(this, "Not implemented yet!", Toast.LENGTH_SHORT).show();
    }

    public void signOutFun(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}