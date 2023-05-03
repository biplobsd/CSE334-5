package com.example.galibtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = findViewById(R.id.input);
        textView= findViewById(R.id.output);
    }

    public void pressPrint(View view) {
        String output = editText.getText().toString();
        double usd = Double.parseDouble(output);
        textView.setText("BDT: "+usd*105);
    }
}