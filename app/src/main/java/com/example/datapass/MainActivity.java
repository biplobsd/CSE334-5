package com.example.datapass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText firstName, secondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);


    }

    public void nextFuc(View view) {
        String fName = firstName.getText().toString().trim();
        String sName = secondName.getText().toString().trim();
        if(fName.length() == 0 || sName.length() == 0){
            Toast.makeText(this, "You entered blank", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("f", fName);
        i.putExtra("s", sName);
        startActivity(i);

    }
}