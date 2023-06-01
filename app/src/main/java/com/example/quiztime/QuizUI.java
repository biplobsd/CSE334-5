package com.example.quiztime;

import static android.content.ContentValues.TAG;
import static com.example.quiztime.QuizLoad.QUIZ_LOAD_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiztime.model.Quiz;
import com.example.quiztime.model.QuizItem;
import com.example.quiztime.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class QuizUI extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public static String DATABASE_KEY = "users";
    Quiz quiz;
    TextView prg, question, tv;
    List<RadioButton> radioButtons = new ArrayList<>(4);
    public int progress = 0;
    List<List<Integer>> scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_ui);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra(QUIZ_LOAD_KEY);

        Toast.makeText(this, quiz.toString(), Toast.LENGTH_SHORT).show();
        tv = findViewById(R.id.quizID);
        question = findViewById(R.id.question);
         prg = findViewById(R.id.quizProgress);

        radioButtons.add(findViewById(R.id.q1));
        radioButtons.add(findViewById(R.id.q2));
        radioButtons.add(findViewById(R.id.q3));
        radioButtons.add(findViewById(R.id.q4));

        scoreList = new ArrayList<>();
        for (int i = 0; i < quiz.getLength(); i++) {
            List<Integer> scoreItem = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                scoreItem.add(0);
            }
            scoreList.add(scoreItem);
        }



        loadQuiz(progress);


    }

    private  void loadQuiz(int position){
        tv.setText("#" + quiz.getId());
        prg.setText(progress+1 +"/"+quiz.getLength());

        List<QuizItem> qI = quiz.getItems();
        Log.d(TAG, "QuizItem" + qI.toString());
        question.setText(qI.get(position).getTitle());
        QuizItem quizItem = qI.get(position);
        List<String> ansQ = quizItem.getOptions();
        for (int i = 0; i < 4; i++) {
            RadioButton rb = radioButtons.get(i);
            rb.setText(ansQ.get(i));
            int finalI = i;
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < 4; j++) {
                        scoreList.get(position).set(j, finalI == j && quizItem.getAnswer() == finalI ? 5 : 0);
                    }
                    Toast.makeText(QuizUI.this, scoreList.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void nextFun(View view) {
        progress++;
        if(progress < quiz.getLength()){
            loadQuiz(progress);
        }else {
            startActivity(new Intent(this, Leaderboard.class));
        }
    }
}