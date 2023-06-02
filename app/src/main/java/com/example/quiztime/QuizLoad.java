package com.example.quiztime;

import static android.content.ContentValues.TAG;

import static com.example.quiztime.EnterName.USER_NAME;
import static com.example.quiztime.model.Constants.FIRESTORE_QUIZ;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quiztime.model.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
        String userName = i.getStringExtra(USER_NAME);

        db.collection(FIRESTORE_QUIZ).whereEqualTo("id", quizId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(!queryDocumentSnapshots.isEmpty()) {
                            List<Quiz> quizzes = queryDocumentSnapshots.toObjects(Quiz.class);
                            Intent i = new Intent(QuizLoad.this, QuizUI.class);
                            i.putExtra(QUIZ_LOAD_KEY, quizzes.get(0));
                            i.putExtra(USER_NAME, userName);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }else{
                            Toast.makeText(QuizLoad.this, "Quiz id not found!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(QuizLoad.this, "Quiz id not found!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}