package com.example.quiztime;

import static android.content.ContentValues.TAG;
import static com.example.quiztime.QuizUI.DATABASE_KEY;
import static com.example.quiztime.model.Constants.FIRESTORE_SCORE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quiztime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard extends AppCompatActivity {
    public static String LEADERBOARD_QUIZ_ID_KEY = "LEADERBOARD_QUIZ_ID_KEY";
    private FirebaseFirestore db;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        int quizID = getIntent().getIntExtra(LEADERBOARD_QUIZ_ID_KEY, 0);
        TextView quizIDView = findViewById(R.id.quizID);
        quizIDView.setText("Quiz ID #"+quizID);

        listView = (ListView) findViewById(R.id.listview);
        db = FirebaseFirestore.getInstance();

        db.collection(FIRESTORE_SCORE)
                .whereEqualTo("quizID", quizID).orderBy("score", Query.Direction.DESCENDING).limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot results = task.getResult();
                            List<User> stringArray = new ArrayList<>();
                            for (QueryDocumentSnapshot document : results) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                User user = document.toObject(User.class);
                                stringArray.add(user);
                            }
                            if(!results.isEmpty()) {
//                                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
//                                        com.google.android.material.R.layout.support_simple_spinner_dropdown_item, stringArray);
                                CustomArrayAdapter adapter = new CustomArrayAdapter(getApplicationContext(), stringArray);
                                listView.setAdapter(adapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}