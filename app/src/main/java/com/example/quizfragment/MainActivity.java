package com.example.quizfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements Communicator {
    public static String BUNDLE_SCORE_KEY = "com.example.quizfragment.bundle.score";
    private Button nextBtn;
    private int qNo = 0;
    private Bundle bundle;
    private int initialScore = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initial fragment
        loadFragment(new FragmentA());
        nextBtn = findViewById(R.id.next);
        bundle = new Bundle();
        bundle.putInt(BUNDLE_SCORE_KEY, initialScore);
    }

    private void loadFragment(Fragment fragment) {
        fragment.setArguments(bundle);
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
                passDataCom(initialScore);
                nextBtn.setText("Next");
                break;
        }
    }

    @Override
    public void passDataCom(int score) {
        bundle.putInt(BUNDLE_SCORE_KEY, score);
    }
}