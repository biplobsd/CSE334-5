package com.example.quizfragment;

import static com.example.quizfragment.leaderboard.USER_NAME_DB_KEY;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class fragmentC extends Fragment {
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_c, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        MainActivity ma = ((MainActivity) getActivity());
        int score = ma.getScore();
        TextView scoreView = view.findViewById(R.id.score);
        scoreView.setText("You get " + score + " out of 10");
        ma.resetScore();

        setScoreToLeaderboard(ma.getUserName(), score);

        Button leaderBtn = view.findViewById(R.id.openLeaderBoard);
        leaderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), leaderboard.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void setScoreToLeaderboard(String userName, int score){
        User user = new User(userName, score);
        mDatabase.child(USER_NAME_DB_KEY).child(userName).setValue(user);
    }
}