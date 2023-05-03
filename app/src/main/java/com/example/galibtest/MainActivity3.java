package com.example.galibtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    EditText h, w;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        h = findViewById(R.id.height);
        w = findViewById(R.id.width);
        output = findViewById(R.id.output);
    }

    public void CalBMI(View view) {
        double hd = Double.parseDouble(h.getText().toString());
        double bmi = Double.parseDouble(w.getText().toString())/ Math.pow(hd/100, 2);
        output.setText("BMI: "+bmi);
    }
}