package com.example.quiztime;

import static android.content.ContentValues.TAG;
import static com.example.quiztime.EnterName.USER_NAME;
import static com.example.quiztime.Leaderboard.LEADERBOARD_QUIZ_ID_KEY;
import static com.example.quiztime.QuizLoad.QUIZ_LOAD_KEY;
import static com.example.quiztime.model.Constants.FIRESTORE_SCORE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiztime.model.Quiz;
import com.example.quiztime.model.QuizItem;
import com.example.quiztime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.List;

public class QuizUI extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public static String DATABASE_KEY = "users";
    Quiz quiz;
    private String userName;
    TextView prg, question, tv;
    List<RadioButton> radioButtons = new ArrayList<>(4);
    public int progress = 0;
    List<List<Integer>> scoreList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_ui);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        quiz = (Quiz) intent.getSerializableExtra(QUIZ_LOAD_KEY);
        userName = intent.getStringExtra(USER_NAME);
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

    private void loadQuiz(int position) {
        tv.setText("#" + quiz.getId());
        prg.setText(progress + 1 + "/" + quiz.getLength());

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

    private void toLeaderBoard() {
        Intent i = new Intent(QuizUI.this, Leaderboard.class);
        i.putExtra(LEADERBOARD_QUIZ_ID_KEY, quiz.getId());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public void nextFun(View view) {
        progress++;
        if (progress < quiz.getLength()) {
            loadQuiz(progress);
        } else {
            String mName = mAuth.getCurrentUser().getDisplayName().trim();
            User user = new User(
                    mName.length() == 0 ? userName : mName, quiz.getId(),
                    scoreList.stream()
                            .flatMapToInt(innerList -> innerList.stream().mapToInt(Integer::intValue))
                            .sum()
            );

            ((Button) view).setEnabled(false);

            db.collection(FIRESTORE_SCORE).whereEqualTo("quizID", quiz.getId()).whereEqualTo("displayName", user.getDisplayName()).limit(1).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot document = queryDocumentSnapshots.getDocuments().get(0);
                        document.getReference().set(user, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                toLeaderBoard();
                            }
                        });
                    } else {
                        db.collection(FIRESTORE_SCORE).add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                toLeaderBoard();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                ((Button) view).setEnabled(true);
                                Toast.makeText(QuizUI.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ((Button) view).setEnabled(true);
                    Toast.makeText(QuizUI.this, "Error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

