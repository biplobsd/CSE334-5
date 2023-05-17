package com.example.quizfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class home extends AppCompatActivity {
    public static final String USER_NAME_KEY  = "userName";
    EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userName = findViewById(R.id.userName);
    }

    public void startQuiz(View view) {
        String userNameTxt = userName.getText().toString().trim();
        if(userNameTxt.length() == 0){
            Toast.makeText(this, "Please enter you name", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(this, MainActivity.class);
        i.putExtra(USER_NAME_KEY, userNameTxt);
        startActivity(i);
    }

    public void openLeaderboard(View view) {
        Intent i = new Intent(this, leaderboard.class);
        startActivity(i);
    }
}