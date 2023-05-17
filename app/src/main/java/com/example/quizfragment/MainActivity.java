package com.example.quizfragment;

import static com.example.quizfragment.home.USER_NAME_KEY;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.stream.IntStream;


public class MainActivity extends AppCompatActivity {
    private Button nextBtn;
    private int qNo = 0;
    private int[] qScore = {0, 0};

    public String getUserName() {
        return userName;
    }

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        userName = i.getStringExtra(USER_NAME_KEY);
        // Initial fragment
        loadFragment(new FragmentA());
        nextBtn = findViewById(R.id.next);
    }

    private void loadFragment(Fragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.fragmentId, fragment).commit();
    }

    public void pressNext(View view) {
        switch (qNo) {
            case 0:
                loadFragment(new fragmentB());
                qNo = 1;
                break;
            case 1:
                loadFragment(new fragmentC());
                qNo = 2;
                nextBtn.setText("Try again");
                break;
            case 2:
                loadFragment(new FragmentA());
                qNo = 0;
                nextBtn.setText("Next");
                break;
        }
    }

    public void scoreAdd(int qNo, String selectedText, String correctText) {
        qScore[qNo] = isCorrect(selectedText, correctText);
    }

    public int getScore(){
        return IntStream.of(qScore).sum();
    }

    public void resetScore(){
        for (int i = 0; i < qScore.length; i++) {
            qScore[i] = 0;
        }
    }

    private int isCorrect(String selectedText, String correctText) {
        if (Objects.equals(selectedText, correctText)) {
            return 5;
        }
        return 0;
    }

}