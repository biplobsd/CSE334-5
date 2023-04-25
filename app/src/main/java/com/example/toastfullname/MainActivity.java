package com.example.toastfullname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText firstName, secondName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
    }

    public void printFullName(View view) {
        String output = firstName.getText().toString() + " " + secondName.getText().toString();
        Toast.makeText(getApplicationContext(),output,Toast.LENGTH_SHORT).show();
    }
}