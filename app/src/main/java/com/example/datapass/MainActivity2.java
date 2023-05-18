package com.example.datapass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    String firstName, secondName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent i = getIntent();
         firstName = i.getStringExtra("f");
         secondName = i.getStringExtra("s");

        Toast.makeText(this, "FirstName : "+firstName +" SecondName : "+secondName, Toast.LENGTH_SHORT).show();
    }

    public void nextFuc(View view) {

        Intent i = new Intent(this, MainActivity3.class);
        i.putExtra("f", firstName);
        i.putExtra("s", secondName);
        startActivity(i);
    }
}