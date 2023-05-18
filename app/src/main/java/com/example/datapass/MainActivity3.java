package com.example.datapass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView firstName = findViewById(R.id.firstName);
        TextView secondName = findViewById(R.id.secondName);

        Intent i = getIntent();
        String fName = i.getStringExtra("f");
        String sName = i.getStringExtra("s");

        firstName.setText(fName);
        secondName.setText(sName);

    }

    public void startAgainFun(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}