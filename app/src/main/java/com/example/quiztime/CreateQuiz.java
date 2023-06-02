package com.example.quiztime;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.quiztime.model.Quiz;
import com.example.quiztime.model.QuizItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CreateQuiz extends AppCompatActivity {
    FirebaseFirestore db;
    FirebaseDatabase database;
    private int length = 0;
    private int position = 0;
    private TextView progress, createQuiz;
    private List<QuizItem> quizItems;

    public Quiz quizRoot;

    void setLength(int l) {
        createQuiz.setText("Create quiz");
        quizItems = new ArrayList<QuizItem>(l);
        progress.setText((position + 1) + "/" + l);
        length = l;
    }

    private void setPos(int pos) {
        position = pos;
        progress.setText(position + "/" + length);
    }

    private void setQuizItem(QuizItem quizItem) {
        quizItems.add(quizItem) ;
        setPos(++position);
    }

    public void changeFragment(QuizItem quizItemInput) {
        setQuizItem(quizItemInput);
        if (position == length) {
            loadFragment(new CreateQuizLoading());
            createQuiz.setText("");
            progress.setText("");

            Quiz quiz = new Quiz(length, quizItems);
            Toast.makeText(this, quiz.toString(), Toast.LENGTH_SHORT).show();

            db.collection("configs").document("path").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        Long total1 = (Long) document.getData().get("total");
                        int to = total1.intValue();
                        quiz.setId(++to);
                        db.collection("quiz")
                                .add(quiz)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        Task<DocumentSnapshot> m = documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot ddd = task.getResult();
                                                    quizRoot = ddd.toObject(Quiz.class);
                                                    db.collection("configs").document("path").update(
                                                            "total", FieldValue.increment(1)
                                                    ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                loadFragment(new CreateQuizFinishFragment());
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                        loadFragment(new CreateQuizLenFragment());
                                    }
                                });
                    }
                }
            });


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);
        progress = findViewById(R.id.progress);
        createQuiz = findViewById(R.id.createQuiz);

        loadFragment(new CreateQuizLenFragment());
        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();

    }

    public void loadFragment(Fragment fm) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fm).commit();
    }

}