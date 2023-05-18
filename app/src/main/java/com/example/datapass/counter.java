package com.example.datapass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class counter extends AppCompatActivity {
    TextView counterUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        counterUpdate = findViewById(R.id.counterUpdate);

    }

    public void plus(View view) {
        Integer i = Integer.parseInt(counterUpdate.getText().toString()) + 1;
        counterUpdate.setText(i.toString());
    }
    public void mus(View view) {
        Integer i = Integer.parseInt(counterUpdate.getText().toString()) - 1;
        counterUpdate.setText(i.toString());
    }
}