package com.example.fragment_listview_quiz;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.Objects;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    int[] qScore = {0, 0, 0};
    String[] mobileArray = {
            "Who is the author of the famous novel \"To Kill a Mockingbird\"?",
            "What is the chemical symbol for the element gold?",
            "In which country is the Taj Mahal located?"
    };
    private TextView liveScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liveScore = findViewById(R.id.liveScore);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, mobileArray);

        ListView listView = (ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadNext(Math.toIntExact(id));
            }
        });

        // Initial fragment
        loadFragment(new q1());
    }

    private void setLiveScore(int score) {
        liveScore.setText("Score : " + score);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentId, fragment).commit();
    }

    public void loadNext(int qNo) {
        switch (qNo) {
            case 0:
                loadFragment(new q1());
                break;
            case 1:
                loadFragment(new q2());
                break;
            case 2:
                loadFragment(new q3());
                break;
        }
        setLiveScore(IntStream.of(qScore).sum());
    }

    public void scoreAdd(int qNo, String selectedText, String correctText) {
        qScore[qNo] = isCorrect(selectedText, correctText);
    }

    private int isCorrect(String selectedText, String correctText) {
        if (Objects.equals(selectedText, correctText)) {
            return 5;
        }
        return 0;
    }

}