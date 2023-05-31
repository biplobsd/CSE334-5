package com.example.quiztime;

import static com.example.quiztime.QuizUI.DATABASE_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.quiztime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Leaderboard extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        listView = (ListView) findViewById(R.id.listview);

        DatabaseReference m = database.getReference(DATABASE_KEY);
        m.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();

                    final ArrayList<User> userList = new ArrayList<>();
                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                        User object = childSnapshot.getValue(User.class);
                        userList.add(object);
                    }

                    Collections.sort(userList, new Comparator<User>() {
                        @Override
                        public int compare(User user1, User user2) {
                            return Integer.compare(user2.getScore(), user1.getScore());
                        }
                    });

                    String[] stringArray = new String[userList.size()];
                    for (int i = 0; i < userList.size(); i++) {
                        stringArray[i] = userList.get(i).toString();
                    }

                    ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),
                            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, stringArray);

                    listView.setAdapter(adapter);
                }
            }
        });
    }
}