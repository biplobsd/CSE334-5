package com.example.galibtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerLanguages = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.dropdownOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerLanguages.setAdapter(adapter);
    }


    public void go(View view) {
        String text = spinnerLanguages.getSelectedItem().toString();
        switch (text){
            case "USD to BDT":
                usdTobdt();
                break;
            case "BMI calculator":
                bmi();
                break;
        }
    }

    private void usdTobdt() {
        Intent switchActivityIntent = new Intent(this, MainActivity2.class);
        startActivity(switchActivityIntent);
    }
    private void bmi() {
        Intent switchActivityIntent = new Intent(this, MainActivity3.class);
        startActivity(switchActivityIntent);
    }
}