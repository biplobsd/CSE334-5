package com.example.quiztime;

import static android.content.ContentValues.TAG;

import static com.example.quiztime.model.Constants.FIRESTORE_QUIZ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.quiztime.model.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class QuizLoad extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String QUIZ_LOAD_KEY = "QUIZ_LOAD_KEY";
    public static String QUIZ_LOAD_ID = "QUIZ_LOAD_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_load);

        Intent i = getIntent();
        int quizId = i.getIntExtra(QUIZ_LOAD_ID, 1);

        db.collection(FIRESTORE_QUIZ).whereEqualTo("id", quizId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot document = task.getResult();
                            List<Quiz> quizzes = document.toObjects(Quiz.class);
                            Intent i = new Intent(QuizLoad.this, QuizUI.class);
                            i.putExtra(QUIZ_LOAD_KEY, quizzes.get(0));
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}